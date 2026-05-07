package com.barabad.albayreality.frontend.utilities.data.quizzes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class AnswerRecord(
    val questionIndex: Int,
    val choiceText: String,
    val timeTaken: Int,   // seconds spent on this question
    val isCorrect: Boolean
)
// # global state model to hold the info on how the user is navigating on the quiz
class QuizState : ViewModel() {


    // Quiz Backend Repository
    private val repository = QuizRepository()

    private val _answerRecords = mutableListOf<AnswerRecord>()
    val answerRecords: List<AnswerRecord> get() = _answerRecords

    fun addAnswerRecord(record: AnswerRecord) {
        _answerRecords.add(record)
    }
    // # 'private set' MEANS THE UI CAN READ THIS NUMBER, BUT ONLY THIS VIEWMODEL CAN MODIFY IT
    var current_item_number by mutableIntStateOf(0)
        private set
    var correct_answered_items by mutableIntStateOf(0)
        private set
    var incorrect_answered_items by mutableIntStateOf(0)
        private set
    var missed_items by mutableIntStateOf(0)
        private set

    var site_id by mutableStateOf("") // # para ma-determine kung kaninong quiz to
        private set

    // # etong active quiz is supplied sa may Playground Screen depending sa site id (refer sa loadQuizForSite() helper functin)
    var active_quiz by mutableStateOf<List<QuizzesModel>>(emptyList()) // # an array of quiz item to, gaya sa datastructe ng Quiz1
        private set

    fun loadQuizForSite(incoming_site_id: String) {
        if (site_id != incoming_site_id) {
            site_id = incoming_site_id
            viewModelScope.launch {
                active_quiz = repository.fetchQuiz(incoming_site_id)
                resetQuiz()
            }
        }
    }

    fun getCurrentItem(): QuizzesModel { // # pagkuha ng current item sa loob ng active quiz
        if (active_quiz.isEmpty() || current_item_number >= active_quiz.size) {
            return QuizzesModel("", "", "", "", "", "")
        }
        return active_quiz[current_item_number]
    }

    fun hasNoRemainingItem(): Boolean { // # pangcheck if tapos na ung quiz
        if (active_quiz.isEmpty()) return true
        return current_item_number >= active_quiz.size - 1
    }

    fun recordScore(status: String) { // # para matrack ung correct, incorrect, and missied items ng user
        when (status) {
            "correct_status" -> correct_answered_items += 1
            "incorrect_status" -> incorrect_answered_items += 1
            "times_up_status" -> missed_items += 1
        }
    }

    fun nextItem() { // # pang navigate ko sa next item sa UI
        if (!hasNoRemainingItem()) {
            current_item_number += 1
        }
    }

    fun resetQuiz() { // # reset para ma-store ng quiz state na to ung next quiz
        current_item_number = 0
        correct_answered_items = 0
        incorrect_answered_items = 0
        missed_items = 0
        _answerRecords.clear()
    }

    fun clearSiteId() {
        site_id = ""
    }
}