package com.example.albert.myapplication;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.example.albert.myapplication.model.Cow;
import com.example.albert.myapplication.model.CowsDatabase;

public class Activity3 extends Activity {
    private EditText txtDate;
    private EditText txtMilkYield;
    private EditText txtMOG;
    private EditText txtWeight;
    private CowsDatabase mCowDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
        init();
    }

    private void init() {
        txtDate = findViewById(R.id.txtDate);
        txtMilkYield = findViewById(R.id.txtMilkYield);
        txtMOG = findViewById(R.id.txtMOG);
        txtWeight = findViewById(R.id.txtWeight);
        mCowDatabase = Room.databaseBuilder(getApplicationContext(), CowsDatabase.class, "cows_db").build();
    }

    public void closeData(View view) {
        Intent intent = new Intent(this, Actvity2.class);
        intent.putExtra("idedit", String.valueOf(getIntent().getStringExtra("id")));
        startActivity(intent);
    }

    public void saveData(View view) {

        class MyTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                String id = getIntent().getStringExtra("id");
                Cow cowbyID = mCowDatabase.daoAccess().getCowbyID(Integer.valueOf(id));
                String csvgraph = cowbyID.getCsvgraph();
                if (csvgraph == null) {
                    csvgraph = "";
                }
                StringBuilder csvgraphForAppend = new StringBuilder(csvgraph);
                String milkYield = txtMilkYield.getText().toString();
                String weight = txtWeight.getText().toString();
                String MOG = txtMOG.getText().toString();
                if (milkYield.equals("")) milkYield="-1";
                if (weight.equals("")) weight="-1";
                if (MOG.equals("")) MOG="-1";
                csvgraphForAppend.append(",Nadoi,").append((milkYield) + ",").
                        append("MOG,").append(MOG + ",").append("Weight,").append(weight+",").
                        append("data,").append(txtDate.getText().toString());
                cowbyID.setCsvgraph(csvgraphForAppend.toString());
                mCowDatabase.daoAccess().update(cowbyID);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }
        new MyTask().execute();
    }
}
