package com.example.basicapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView timerTextView;
    private Button startButton, stopButton, resetButton;

    private boolean running = false;
    private long startTime = 0L;
    private long elapsedTime = 0L;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime;
            int seconds = (int) (elapsedTime / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            int milliseconds = (int) (elapsedTime % 1000);
            timerTextView.setText(String.format("%d:%02d:%03d", minutes, seconds, milliseconds));
            handler.postDelayed(this, 10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    startTime = System.currentTimeMillis() - elapsedTime;
                    handler.postDelayed(runnable, 10);
                    running = true;
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    handler.removeCallbacks(runnable);
                    running = false;
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    elapsedTime = 0L;
                    timerTextView.setText("0:00:000");
                    Toast.makeText(MainActivity.this, "Thanks for using my app", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}