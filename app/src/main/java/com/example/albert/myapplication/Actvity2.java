package com.example.albert.myapplication;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.albert.myapplication.helpers.AgeCalculator;
import com.example.albert.myapplication.helpers.ForGraph;
import com.example.albert.myapplication.helpers.GraphOneStep;
import com.example.albert.myapplication.model.Cow;
import com.example.albert.myapplication.model.CowsDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Actvity2 extends Activity {
    private EditText birka;
    private EditText poroda;
    private EditText birthday;
    private EditText mast;
    private EditText mother;
    private EditText father;
    private CowsDatabase mCowDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        mCowDatabase = Room.databaseBuilder(getApplicationContext(), CowsDatabase.class, "cows_db").build();
        init();
    }

    @Override
    protected void onResume() {
        if (getIntent().getStringExtra("idedit") != null) {
            String idedit = getIntent().getStringExtra("idedit");
            class MyTaskEdit extends AsyncTask<Void, Void, Void> {
                @Override
                protected Void doInBackground(Void... voids) {
                    Cow cowFordedit = mCowDatabase.daoAccess().getCowbyID(Integer.valueOf(getIntent().
                            getStringExtra("idedit")));
                    System.out.println(cowFordedit.toString());
                    birka.setText(String.valueOf(cowFordedit.getId()));
                    birthday.setText(cowFordedit.getBirthday());
                    mast.setText(cowFordedit.getMast());
                    mother.setText(cowFordedit.getMother());
                    poroda.setText(cowFordedit.getPoroda());
                    father.setText(cowFordedit.getFather());

                    ForGraph forGraph = new ForGraph(cowFordedit.getCsvgraph());
                    if (forGraph.isReadyToGraph()) {
                        List<GraphOneStep> listOfGraphSteps = forGraph.getListOfGraphSteps();
                        GraphView graph = (GraphView) findViewById(R.id.graph);
                        DataPoint[] dataPointsMOG = new DataPoint[listOfGraphSteps.size()];
                        DataPoint[] dataPointsWeight = new DataPoint[listOfGraphSteps.size()];
                        DataPoint[] dataPointsNadoi = new DataPoint[listOfGraphSteps.size()];
                        int r = 0;
                        for (GraphOneStep listOfGraphStep : listOfGraphSteps) {
                            dataPointsMOG[r] = new DataPoint(listOfGraphStep.getDate(),
                                    listOfGraphStep.getMOG());
                            dataPointsWeight[r] = new DataPoint(listOfGraphStep.getDate(),
                                    listOfGraphStep.getWeight());
                            dataPointsNadoi[r] = new DataPoint(listOfGraphStep.getDate(),
                                    listOfGraphStep.getNadoi());
                            r++;
                        }
                        LineGraphSeries<DataPoint> seriesMOG = new LineGraphSeries<>(dataPointsMOG);
                        seriesMOG.setTitle("МОЖ");
                        seriesMOG.setColor(Color.GREEN);
                        LineGraphSeries<DataPoint> seriesWeight = new LineGraphSeries<>(dataPointsWeight);
                        seriesWeight.setTitle("Вес");
                        seriesWeight.setColor(Color.RED);
                        LineGraphSeries<DataPoint> seriesNadoi = new LineGraphSeries<>(dataPointsNadoi);
                        seriesNadoi.setTitle("Надой");
                        seriesNadoi.setColor(Color.BLACK);
                        graph.addSeries(seriesMOG);
                        graph.addSeries(seriesNadoi);
                        graph.addSeries(seriesWeight);
                        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
                        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
                        graph.getLegendRenderer().setVisible(true);
                        graph.getGridLabelRenderer().reloadStyles();
                        graph.getViewport().setMinX(dataPointsMOG[0].getX());
                        graph.getViewport().setMaxX(dataPointsMOG[dataPointsMOG.length - 1].getX());
                        graph.getViewport().setXAxisBoundsManual(true);
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                }
            }
            new MyTaskEdit().execute();

        }
        super.onResume();
    }

    public void close(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void init() {
        mother = findViewById(R.id.txtMother);
        father = findViewById(R.id.txtFather);
        mast = findViewById(R.id.txtMast);
        birthday = findViewById(R.id.txtBirthDay);
        poroda = findViewById(R.id.txtPoroda);
        birka = findViewById(R.id.txtBirka);
    }


    public void save(View view) {
        class MyTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = parser.parse(birthday.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace(); }

                String age = AgeCalculator.getAge(date);

                Cow cow = new Cow(Integer.parseInt(birka.getText().toString()), poroda.getText().toString(),
                        mast.getText().toString(),
                        age, birthday.getText().toString(), mother.getText().toString(),
                        father.getText().toString(), null);
                if (mCowDatabase.daoAccess().getCowbyID((Integer.parseInt(birka.getText().toString()))) == null) {
                    mCowDatabase.daoAccess().addNote(cow);
                    Log.d("as", "database");
                } else {
                    mCowDatabase.daoAccess().update(cow); }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }
        new MyTask().execute();
    }

    public void chart(View view) {
        Intent intent = new Intent(this, Activity3.class);
        intent.putExtra("id", birka.getText().toString());
        startActivity(intent);
    }
}
