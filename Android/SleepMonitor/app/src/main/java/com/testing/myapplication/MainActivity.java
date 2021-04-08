package com.testing.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


class LineChartXAxisValueFormatter extends IndexAxisValueFormatter {

    @Override
    public String getFormattedValue(float value) {

        // Convert float value to date string
        // Convert from seconds back to milliseconds to format time  to show to the user
        long emissionsMilliSince1970Time = ((long) value) * 1000;

        // Show time in local version
        Date timeMilliseconds = new Date(emissionsMilliSince1970Time);
//        DateFormat dateTimeFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        DateFormat dateTimeFormat = DateFormat.getDateTimeInstance();

        return dateTimeFormat.format(timeMilliseconds);
    }
}

public class MainActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {
    Button ButtonFetchDataTest;
    ProgressBar progressBar;
    private static final String TAG = "MainActivity";
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonFetchDataTest = findViewById(R.id.ButtonFetchDataTest);
        progressBar = findViewById(R.id.progress);



        mChart = (LineChart) findViewById(R.id.tempLineChart);

        mChart.setOnChartGestureListener(MainActivity.this);
        mChart.setOnChartValueSelectedListener(MainActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        LimitLine upper_limit = new LimitLine(65f,"Danger");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f,10f,10f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);


        LimitLine lower_limit = new LimitLine(35f,"Too Low");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f,10f,10f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(15f);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(25f);
        leftAxis.enableGridDashedLine(10f,10f,0);
        leftAxis.setDrawLimitLinesBehindData(true);


        mChart.getAxisRight().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();


        yValues.add(new Entry(0,60f));
        yValues.add(new Entry(100,50f));
        yValues.add(new Entry(200,70f));
        yValues.add(new Entry(300,30f));
        yValues.add(new Entry(400,50f));
        yValues.add(new Entry(500,60f));
        yValues.add(new Entry(600,65f));

        LineDataSet set1 = new LineDataSet(yValues,"Data set 1");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.BLACK);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        mChart.setData(data);


//        String[] values = new String[]{"Jan","Feb","Mar","Apr","May","June","July"};

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new LineChartXAxisValueFormatter());
        xAxis.setLabelRotationAngle(-90);
       // xAxis.setGranularity(1);


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

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}

//class MyXAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter {
//    private String[] mValues;
//    public MyXAxisValueFormatter(String[] values){
//        this.mValues = values;
//    }
//
//    @Override
//    public String getFormattedValue(float value, AxisBase axis){
//        return mValues[(int)value];
//    }
//}