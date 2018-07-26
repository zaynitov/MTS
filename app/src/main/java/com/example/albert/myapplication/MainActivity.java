package com.example.albert.myapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.albert.myapplication.helpers.ConvertorToListForRecycleView;
import com.example.albert.myapplication.model.Cow;
import com.example.albert.myapplication.model.CowsDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Cow> cows;
    private CowsDatabase mCowDatabase;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mCowDatabase = Room.databaseBuilder(getApplicationContext(), CowsDatabase.class, "cows_db").
                allowMainThreadQueries()
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        updateUI();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void updateUI() {
        class MyTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                cows = mCowDatabase.daoAccess().getCows();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (mAdapter == null) {
                    mAdapter = new CustomAdapter(ConvertorToListForRecycleView.convert(cows), context);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.setList(ConvertorToListForRecycleView.convert(cows));
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
        new MyTask().execute();
    }

    public void addcow(View view) {
        Intent intent = new Intent(this, Actvity2.class);
        startActivity(intent);
    }
}
