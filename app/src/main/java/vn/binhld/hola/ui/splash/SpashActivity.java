package vn.binhld.hola.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import vn.binhld.hola.R;
import vn.binhld.hola.ui.main.MainActivity;

public class SpashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMainActivity();
        finish();
    }

    public void startMainActivity() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    public void startLoginActivity() {

    }

    // todo: check network configuration
}