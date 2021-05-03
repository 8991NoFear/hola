package vn.binhld.hola.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
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
import com.google.firebase.auth.UserProfileChangeRequest;

import vn.binhld.hola.R;
import vn.binhld.hola.helper.RegexCheck;
import vn.binhld.hola.model.User;
import vn.binhld.hola.repository.AppRepository;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = SignupActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

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
                    Log.d(TAG, "createUserWithEmail:success");

                    mUser = mAuth.getCurrentUser();
                    String displayName = mUser.getDisplayName();
                    if (displayName == null) {
                        displayName = mUser.getEmail().split("@")[0];
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(displayName)
                                .setPhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/hola-4caec.appspot.com/o/default_avatar.jpg?alt=media&token=99a9349e-d74f-4496-9dcd-37e7d4a96791"))
                                .build();
                        mUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User profile updated.");
                                }
                            }
                        });
                    }

                    User newUser = new User(displayName, null, null, null);
                    AppRepository.getInstance().pushUser(newUser);

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

    @Override
    protected void onResume() {
        super.onResume();
        mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            success();
        }
    }
}