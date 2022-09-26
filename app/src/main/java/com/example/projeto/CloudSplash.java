package com.example.projeto;
// Criado por Júlia Martins
// Data: 19/09/2022
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.projeto.model.MainClient;

import java.util.Timer;
import java.util.TimerTask;

public class CloudSplash extends AppCompatActivity {

    private final Timer timer = new Timer();
    TimerTask timerTask;

    // classe criada para a função Splash, tela aberta por 3 segundos ao abrir o app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_splash);

        timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bodySplashMainActivity();
                    }
                });
            }
        };
        timer.schedule(timerTask, 3000);
    }

    private void bodySplashMainActivity() {
        Intent intent = new Intent(getApplicationContext(), View.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}