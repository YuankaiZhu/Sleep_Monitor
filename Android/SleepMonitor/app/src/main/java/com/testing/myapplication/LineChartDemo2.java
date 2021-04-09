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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class LineChartDemo2 extends AppCompatActivity {
    ProgressBar progressBar;

    LineChart mpLineChart;
    Button Home;
    Button Temperature;
    Button Motion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart_demo2);

        progressBar = findViewById(R.id.progress);
        Home = findViewById(R.id.Home);
        Temperature = findViewById(R.id.btnLineChart1);
        Motion= findViewById(R.id.btnLineChart2);


        mpLineChart = (LineChart) findViewById(R.id.line_chart2);
        LineDataSet lineDataSet1 = new LineDataSet(FetchMotion(),"Data Set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();

        //Home button function
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start ProgressBar first (Set visibility VISIBLE)
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"LineChartTest",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        //End Write and Read data with URL
                    }
                });
            }
        });
        //temperature button function
        Temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        //Sleep Motion button function
        Motion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start ProgressBar first (Set visibility VISIBLE)
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"LineChartTest",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LineChartDemo2.class);
                        startActivity(intent);
                        finish();
                        //End Write and Read data with URL
                    }
                });
            }
        });
    }
    private ArrayList<Entry> FetchMotion ()
    {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        FetchData fetchData = new FetchData("http://122.239.216.61/FetchData/FetchData.php");
        if (fetchData.startFetch()) {
            //progressBar.setVisibility(View.VISIBLE);
            if (fetchData.onComplete()) {
                String result = fetchData.getResult();
                try {
                    JSONArray arr = new JSONArray(result);
                    for(int i = 0; i < arr.length(); i++){

                        if (arr.getJSONObject(i).getString("id").equals("null")||arr.getJSONObject(i).getString("accel_x").equals("null"))
                        {
                            continue;
                        }
                        float id_v = 0f;
                        float a_x= 0f,a_y= 0f,a_z= 0f,g_x= 0f,g_y= 0f,g_z = 0f,temp_mean = 0f;
                        id_v = Float.parseFloat(arr.getJSONObject(i).getString("id"));
                        a_x = Float.parseFloat(arr.getJSONObject(i).getString("accel_x"));
                        a_y = Float.parseFloat(arr.getJSONObject(i).getString("accel_y"));
                        a_z = Float.parseFloat(arr.getJSONObject(i).getString("accel_z"));
                        g_x = Float.parseFloat(arr.getJSONObject(i).getString("gyro_x"));
                        g_y = Float.parseFloat(arr.getJSONObject(i).getString("gyro_y"));
                        g_z = Float.parseFloat(arr.getJSONObject(i).getString("gyro_z"));
                        temp_mean = Math.abs((a_x+a_y+a_z+g_x+g_y+g_z)/6);
                        dataVals.add(new Entry(id_v,temp_mean));
                    }
                    Toast.makeText(getApplicationContext(),"Fetch Success",Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"JSON Error",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
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
        progressBar.setVisibility(View.GONE);
        return  dataVals;
    }
}