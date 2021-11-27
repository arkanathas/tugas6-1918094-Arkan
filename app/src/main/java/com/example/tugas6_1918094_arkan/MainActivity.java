package com.example.tugas6_1918094_arkan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int counter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(5000, 1000){
                    public void onTick(long millisUntilFinished){
                        textView.setText(String.valueOf(counter));
                        counter++;
                    }
                    public void onFinish(){
                        textView.setText("FINISH!!");
                        WorkManager.getInstance().enqueueUniqueWork("Notifikasi",
                                ExistingWorkPolicy.REPLACE, request);
                    }
                }.start();
            }
        });
    }

}