package com.example.albert.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list;
    private Context context;

    public void setList(List<String> list) {
        this.list = list;
    }

    public CustomAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        //for id, to understand which row was clicked
        if (position / 4 > 0) {
            viewHolder.setId(Integer.valueOf(list.get((position / 4) * 4)));
        }
        else viewHolder.setId(0);
        viewHolder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public ViewHolder(final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (id==0) return; //if id=0 do nothing(for title)
                        Intent intent = new Intent(itemView.getContext(), Actvity2.class);
                        intent.putExtra("idedit", String.valueOf(id));
                        itemView.getContext().startActivity(intent);
                    }
                });



        }
    }
}