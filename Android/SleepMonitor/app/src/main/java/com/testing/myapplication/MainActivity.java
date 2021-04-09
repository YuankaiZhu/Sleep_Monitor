package com.testing.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


//class LineChartXAxisValueFormatter extends IndexAxisValueFormatter {
//
//    @Override
//    public String getFormattedValue(float value) {
//
//        // Convert float value to date string
//        // Convert from seconds back to milliseconds to format time  to show to the user
//        long emissionsMilliSince1970Time = ((long) value) * 1000;
//
//        // Show time in local version
//        Date timeMilliseconds = new Date(emissionsMilliSince1970Time);
////        DateFormat dateTimeFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
//        DateFormat dateTimeFormat = DateFormat.getDateTimeInstance();
//
//        return dateTimeFormat.format(timeMilliseconds);
//    }
//}

public class MainActivity extends AppCompatActivity  {
    Button LineChart;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart = findViewById(R.id.btnLineChart);
        progressBar = findViewById(R.id.progress);


        LineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch Data
                final char Time;
                //Start ProgressBar first (Set visibility VISIBLE)
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"LineChartTest",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LineChartDemo1.class);
                        startActivity(intent);
                        finish();
                        //End Write and Read data with URL
                    }
                });
                }
        });
    }
}