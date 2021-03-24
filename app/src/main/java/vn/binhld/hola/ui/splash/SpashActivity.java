package vn.binhld.hola.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import vn.binhld.hola.R;
import vn.binhld.hola.ui.main.MainActivity;

public class SpashActivity extends AppCompatActivity {
    private final int SPLASH_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SPLASH_DURATION);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startMainActivity();
            }
        });
        thread.start();
    }

    public void startMainActivity() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    public void startLoginActivity() {

    }

    // todo: check network configuration
}