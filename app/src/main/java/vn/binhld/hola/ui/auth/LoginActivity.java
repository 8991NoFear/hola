package vn.binhld.hola.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vn.binhld.hola.R;
import vn.binhld.hola.helper.RegexCheck;
import vn.binhld.hola.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    // request code
    private static final int RC_SIGN_UP = 1;
    private static final int RC_FORGET = 2;

    private static final String TAG = LoginActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    // views
    private TextInputEditText emailEditText;
    private TextInputLayout emailLayout;

    private TextInputEditText passwordEditText;
    private TextInputLayout passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        emailEditText = findViewById(R.id.email_edit_text);
        emailLayout = findViewById(R.id.email_layout);

        passwordEditText = findViewById(R.id.password_edit_text);
        passwordLayout = findViewById(R.id.password_layout);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View v) {
        emailLayout.setError(null);
        passwordLayout.setError(null);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || !RegexCheck.validateEmail(email)) {
            emailLayout.setError(getString(R.string.err_email_format));
            return;
        } else if (password.isEmpty() || password.length() < 6) {
            passwordLayout.setError(getString(R.string.err_password_too_short));
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    success();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    fail();
                }
            }
        });
    }


    public void signup(View v) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivityForResult(intent, RC_SIGN_UP);
    }

    public void forget(View v) {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivityForResult(intent, RC_FORGET);
    }

    private void success() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    private void fail() {
        emailLayout.setError(getString(R.string.err_username_password));
        passwordLayout.setError(getString(R.string.err_username_password));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_UP) {
            if (resultCode == RESULT_OK) {
                success();
            }
        } else if (requestCode == RC_FORGET) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = mAuth.getCurrentUser();
        if (user != null) {
            success();
        }
    }
}