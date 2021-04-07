package com.testing.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MainActivity extends AppCompatActivity {
    Button ButtonFetchDataTest;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonFetchDataTest = findViewById(R.id.ButtonFetchDataTest);
        progressBar = findViewById(R.id.progress);

        ButtonFetchDataTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final char Time;
                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            FetchData fetchData = new FetchData("http://122.239.219.224/Test/FetchTest.php");
                            if (fetchData.startFetch()) {

                                if (fetchData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = fetchData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),TestResult.class);

                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Error Catch",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Cannot fetch",Toast.LENGTH_SHORT).show();

                            }
                            //End Write and Read data with URL
                        }
                    });
                }
        });
    }
}