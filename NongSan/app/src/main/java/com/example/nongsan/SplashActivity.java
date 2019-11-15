package com.example.nongsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    private Button btnLogin, btnRegister;
    private ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        addControls();
        progress.setVisibility(ProgressBar.INVISIBLE);
        /// on click button Dang nhap
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setIndeterminate(true);
                progress.setVisibility(ProgressBar.VISIBLE);
                Intent intent = new Intent(SplashActivity.this, ChatActivity.class);
                SplashActivity.this.startActivity(intent);
            }
        });

        /// on click button dang ky
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setIndeterminate(true);
                progress.setVisibility(ProgressBar.VISIBLE);
                Intent intent = new Intent(SplashActivity.this, DangKyActivity.class);
                SplashActivity.this.startActivity(intent);
            }
        });

    }

    private void addControls() {
        progress = findViewById(R.id.progress);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

    }
}
