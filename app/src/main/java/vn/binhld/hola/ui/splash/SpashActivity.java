package vn.binhld.hola.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vn.binhld.hola.ui.auth.LoginActivity;
import vn.binhld.hola.ui.main.MainActivity;

public class SpashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startMainActivity();
        } else {
            startLoginActivity();
        }
        finish();
    }

    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void startMainActivity() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    // todo: check network configuration
}