package vn.binhld.hola.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vn.binhld.hola.R;
import vn.binhld.hola.helper.RegexCheck;

public class ForgetPasswordActivity extends AppCompatActivity {
    private static final String TAG = ForgetPasswordActivity.class.getSimpleName();
    // firebase
    FirebaseAuth mAuth;
    FirebaseUser user;

    // views
    TextInputLayout emailLayoutF;
    TextInputEditText emailEditTextF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        getSupportActionBar().setTitle(R.string.title_actionbar_f);

        emailLayoutF = findViewById(R.id.email_layout_f);
        emailEditTextF = findViewById(R.id.email_edit_text_f);

        mAuth = FirebaseAuth.getInstance();
    }

    private void success() {
        setResult(RESULT_OK);
        finish();
    }

    public void sendEmail(View view) {
        emailLayoutF.setError(null);

        String email = emailEditTextF.getText().toString();

        if (email.isEmpty() || !RegexCheck.validateEmail(email)) {
            emailLayoutF.setError(getString(R.string.err_email_format));
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Email sent.");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPasswordActivity.this);
                    builder.setTitle(R.string.alert_send_reset_password_email_title)
                            .setMessage(R.string.alert_send_reset_password_email_body)
                            .setPositiveButton(R.string.alert_send_reset_password_email_positive_button, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    success();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
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