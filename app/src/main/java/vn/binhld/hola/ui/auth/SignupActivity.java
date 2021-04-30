package vn.binhld.hola.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = SignupActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    // views
    private TextInputLayout emailLayoutS;
    private TextInputLayout passwordLayoutS;
    private TextInputLayout repasswordLayoutS;

    private TextInputEditText emailEditTextS;
    private TextInputEditText passwordEditTextS;
    private TextInputEditText repasswordEditTextS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle(R.string.title_actionbar_s);

        emailLayoutS = findViewById(R.id.email_layout_s);
        passwordLayoutS = findViewById(R.id.password_layout_s);
        repasswordLayoutS = findViewById(R.id.repassword_layout_s);

        emailEditTextS = findViewById(R.id.email_edit_text_s);
        passwordEditTextS = findViewById(R.id.password_edit_text_s);
        repasswordEditTextS = findViewById(R.id.repassword_edit_text_s);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            success();
        }
    }

    public void signup(View v) {

        emailLayoutS.setError(null);
        passwordLayoutS.setError(null);
        repasswordLayoutS.setError(null);

        String email = emailEditTextS.getText().toString();
        String password = passwordEditTextS.getText().toString();
        String repassword = repasswordEditTextS.getText().toString();

        if (email.isEmpty() || !RegexCheck.validateEmail(email)) {
            emailLayoutS.setError(getString(R.string.err_email_format));
            return;
        } else if (password.isEmpty() || password.length() < 6) {

            passwordLayoutS.setError(getString(R.string.err_password_too_short));
            return;
        } else if (!repassword.equals(password)) {
            repasswordLayoutS.setError(getString(R.string.err_repassword));
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    success();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    fail();
                }
            }
        });
    }

    public void success() {
        setResult(RESULT_OK);
        finish();
    }

    public void fail() {
        emailLayoutS.setError(getString(R.string.err_email_exits));
    }

}