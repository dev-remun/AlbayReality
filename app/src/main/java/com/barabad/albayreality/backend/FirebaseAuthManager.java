package com.barabad.albayreality.backend;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;

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
                .setUrl("https://firebaseapp.com")
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
    // Callback interface
    public interface AuthCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }
}