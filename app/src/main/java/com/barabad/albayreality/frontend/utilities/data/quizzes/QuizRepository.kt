package com.barabad.albayreality.frontend.utilities.data.quizzes

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class QuizRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun fetchQuiz(siteId: String): List<QuizzesModel> {
        Log.d("QuizRepo", "Fetching quiz for $siteId")
        return try {
            val document = db.collection("quizzes")
                .document(siteId)
                .get()
                .await()

            if (!document.exists()) {
                Log.e("QuizRepo", "Document does NOT exist for $siteId")
                return emptyList()
            }

            val questions = document.get("questions") as? List<Map<String, Any>>
            Log.d("QuizRepo", "Got ${questions?.size ?: 0} questions")

            questions?.map { q ->
                val choices = q["choices"] as? List<Map<String, Any>> ?: emptyList()
                val optionTexts = choices.map { it["choiceText"] as? String ?: "" }
                val correctChoice = choices.find { it["isCorrect"] == true }
                val correctText = correctChoice?.get("choiceText") as? String ?: ""

                val paddedOptions = (optionTexts + List(4) { "" }).take(4)

                QuizzesModel(
                    question = q["questionText"] as? String ?: "",
                    option1 = paddedOptions[0],
                    option2 = paddedOptions[1],
                    option3 = paddedOptions[2],
                    option4 = paddedOptions[3],
                    correctAnswer = correctText
                )
            } ?: emptyList()
        } catch (e: Exception) {
            Log.e("QuizRepo", "Error fetching quiz", e)
            emptyList()
        }
    }

    suspend fun saveAttempt(
        userId: String,
        siteId: String,
        totalTimeTaken: Int,
        answerRecords: List<AnswerRecord>,
        correctCount: Int,
        incorrectCount: Int,
        missedCount: Int
    ) {
        val db = FirebaseFirestore.getInstance()
        val attemptData = hashMapOf(
            "quizId" to siteId,
            "totalTimeTaken" to totalTimeTaken,
            "correct" to correctCount,
            "incorrect" to incorrectCount,
            "missed" to missedCount,
            "answers" to answerRecords.map { record ->
                mapOf(
                    "questionIndex" to record.questionIndex,
                    "choiceText" to record.choiceText,
                    "isCorrect" to record.isCorrect,
                    "timeTaken" to record.timeTaken
                )
            },
            "dateTaken" to FieldValue.serverTimestamp()
        )
        db.collection("users")
            .document(userId)
            .collection("attempts")
            .add(attemptData)
            .await()
    }

    suspend fun getAttemptCount(userId: String, siteId: String): Int {
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("users")
            .document(userId)
            .collection("attempts")
            .whereEqualTo("quizId", siteId)
            .get()
            .await()
        return query.size()
    }
}

data class QuizAttempt(
    val correct_count: Int = 0,
    val incorrect_count: Int = 0,
    val missed_count: Int = 0,
    val total_time_taken: Int = 0
)

suspend fun getPastAttempts(user_id: String, site_id: String): List<QuizAttempt> {
    val db = FirebaseFirestore.getInstance()
    return try {
        val snapshot = db.collection("users")
            .document(user_id)
            .collection("attempts")
            .whereEqualTo("quizId", site_id)
            .get()
            .await()

        snapshot.documents.mapNotNull { doc ->
            val correct = doc.getLong("correct")?.toInt() ?: 0
            val incorrect = doc.getLong("incorrect")?.toInt() ?: 0
            val missed = doc.getLong("missed")?.toInt() ?: 0
            val time = doc.getLong("totalTimeTaken")?.toInt() ?: 0
            QuizAttempt(correct, incorrect, missed, time)
        }
    } catch (e: Exception) {
        Log.e("QuizRepo", "error fetching attempts", e)
        emptyList()
    }
}