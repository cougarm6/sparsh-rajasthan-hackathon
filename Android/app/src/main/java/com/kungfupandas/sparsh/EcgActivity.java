package com.kungfupandas.sparsh;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class EcgActivity extends AppCompatActivity {

    LineChartView mChart;
    List<PointValue> pointValues = new ArrayList<>();
    int maxNumberOfPoints = 50;
    Handler mHandler = new Handler();
    private float currentVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg);
        mChart = (LineChartView) findViewById(R.id.chart);

        mChart.setInteractive(true);
        mChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        mChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

        List<Line> lines = new ArrayList<>();
        Line line = new Line();
        line.setHasLines(true);
        line.setHasPoints(true);
        line.setColor(getResources().getColor(R.color.colorPrimary));
        lines.add(line);
        LineChartData data = new LineChartData(lines);

        Axis axisX = new Axis().setName("Time");
        Axis axisY = new Axis().setHasLines(true).setName("Volts");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        mChart.setLineChartData(data);
//        drawGraph();
        getEcgData();
    }

    private void getEcgData(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Socket socket = null;
                String response = "";
                try {
                    socket = new Socket("172.20.10.5", 65526);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                            1024);
                    byte[] buffer = new byte[1024];

                    int bytesRead;
                    InputStream inputStream = socket.getInputStream();
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                        response += byteArrayOutputStream.toString("UTF-8");
                        final String res = response;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("response",""+res);
                                try {
                                    currentVal = Float.parseFloat(res);
                                    drawGraph();
                                }catch (Exception e){e.printStackTrace();}
                            }
                        });
                    }

                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    response = "UnknownHostException: " + e.toString();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    response = "IOException: " + e.toString();
                } finally {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("Lol","onPre");
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.e("Lol","onPost");
            }
        }.execute();
    }

    private void drawGraph() {
        mHandler.postDelayed(mRunnable, 32);
    }

    Runnable mRunnable = new Runnable() {
        private int i = 0;
        @Override
        public void run() {
            float yValue = (float) (Math.random() * 100);
            LineChartData data;
            if(mChart.getLineChartData()==null)
                 data = new LineChartData();
            else
                data = new LineChartData(mChart.getLineChartData());
            pointValues.add(new PointValue(i, yValue));
            data.getLines().get(0).setValues(new ArrayList<>(pointValues));
            mChart.setLineChartData(data);
            setViewport();
            if(i < 300){
                mHandler.postDelayed(this, 32);
            }
            i++;
        }
    };

    private void setViewport() {
        int size = pointValues.size();
        if (size > maxNumberOfPoints) {
            final Viewport viewport = new Viewport(mChart.getMaximumViewport());
            viewport.left = size - 50;
            mChart.setCurrentViewport(viewport);
        }
    }
}
