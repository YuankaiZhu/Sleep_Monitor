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
import org.json.JSONArray;
import org.json.JSONException;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import java.util.ArrayList;
import java.util.List;

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
                            FetchData fetchData = new FetchData("http://122.239.219.224/FetchData/FetchData.php");
                            if (fetchData.startFetch()) {
                                if (fetchData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = fetchData.getResult();
                                    try {
                                        JSONArray arr = new JSONArray(result);
//                                        List<String> list = new ArrayList<String>();
//                                        for(int i = 0; i < arr.length(); i++){
//                                            list.add(arr.getJSONObject(i));
//                                        }
                                        System.out.println(arr);
                                    } catch (JSONException e) {
                                        Toast.makeText(getApplicationContext(),"JSON Error",Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
//                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    //TODO:No Use
//                                    Intent intent = new Intent(getApplicationContext(),TestResult.class);
//                                    startActivity(intent);
//                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(),"fetchData.onComplete() Error",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),"fetchData.startFetch() Error",Toast.LENGTH_SHORT).show();
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
        });
    }
}