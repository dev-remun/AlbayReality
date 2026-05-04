package com.barabad.albayreality.backend;
import static com.barabad.albayreality.frontend.utilities.data.quizzes.Quiz1Kt.getQuiz1List;
import static com.barabad.albayreality.frontend.utilities.data.quizzes.Quiz2Kt.getQuiz2List;
import static com.barabad.albayreality.frontend.utilities.data.quizzes.Quiz3Kt.getQuiz3List;

import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizzesModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.firestore.FirebaseFirestore;
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseAuthManager {

    // firebase initialization
    private FirebaseAuth mAuth;

    public FirebaseAuthManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    // Register function from mAuth
    public void registerUser(String email, String password, AuthCallback callback) {
        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                // URL you want to redirect back to. The domain (www.example.com) for this
                // URL must be whitelisted in the Firebase Console.
                .setUrl("https://albay-reality.web.app/")
                // This must be true
                .setHandleCodeInApp(true)
                .setAndroidPackageName(
                        "com.barabad.albayreality",
                        true, /* installIfNotAvailable */
                        "12"    /* minimumVersion */)
                .build();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess();
                        } else {
                            callback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }
    // Login function
    public void loginUser(String email, String password, AuthCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess();
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }
    public void saveQuizResult(
            String userId,
            String siteId,
            int correct,
            int incorrect,
            int missed
    ) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("siteId", siteId);
        result.put("correct", correct);
        result.put("incorrect", incorrect);
        result.put("missed", missed);
        result.put("timeTaken", Timestamp.now());

        db.collection("QuizAttempts")
                .add(result);
    }

    public void seedAllQuizzes() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Maps hardcoded data (Quiz1-4.kt) to Firestore
        seedQuiz(db, "cagsawa_church", "Cagsawa Ruins Quiz", 5, getQuiz1List());
        seedQuiz(db, "old_albay_hall", "Old Albay Hall Quiz", 5, getQuiz2List());
        seedQuiz(db, "st_john_church", "St. John the Baptist Church Quiz", 5, getQuiz3List());
    }

    private void seedQuiz(FirebaseFirestore db, String siteId, String quizName, int timeLimit, List<QuizzesModel> items) {
        List<Map<String, Object>> questions = new ArrayList<>();
        for (QuizzesModel item : items) {
            List<Map<String, Object>> choices = new ArrayList<>();
            // order: option1..4
            String[] options = {item.getOption1(), item.getOption2(), item.getOption3(), item.getOption4()};
            for (String opt : options) {
                Map<String, Object> choice = new HashMap<>();
                choice.put("choiceText", opt);
                choice.put("isCorrect", opt.equals(item.getCorrectAnswer()));
                choices.add(choice);
            }
            Map<String, Object> question = new HashMap<>();
            question.put("questionText", item.getQuestion());
            // question.put("points", 10); Wala pang pts sa frontend, placeholder kaya pa ba ng pts system? HAHAHA
            question.put("choices", choices);
            questions.add(question);
        }
        Map<String, Object> quizData = new HashMap<>();
        quizData.put("quizName", quizName);
        quizData.put("timeLimit", timeLimit);
        quizData.put("questions", questions);

        db.collection("quizzes").document(siteId).set(quizData);
    }


    // Callback interface
    public interface AuthCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }
}
