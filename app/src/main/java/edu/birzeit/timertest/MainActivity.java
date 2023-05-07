package edu.birzeit.timertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 5000; // 5 seconds
    private TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeTextView = findViewById(R.id.time_text_view);
        startTimer();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int count = -1;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeTextView.setText("Time: " + count + " seconds");
                    }
                });
                count++;
            }
        }, 0, 1000);
    }
}
