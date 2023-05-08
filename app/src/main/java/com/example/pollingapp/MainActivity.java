package com.example.pollingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);

        /*
         *Timerを使ったポーリングを始める
         */
        startService((new Intent(getBaseContext(), MyService.class)));

        /*
         * Handlerを使ったポーリング
         */
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                // UIスレッド
                count++;
                textView.setText("text" + count);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(r);


    }
}