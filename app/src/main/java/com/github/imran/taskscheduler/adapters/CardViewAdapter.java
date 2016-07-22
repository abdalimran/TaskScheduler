package com.github.imran.taskscheduler.adapters;

import com.github.imran.taskscheduler.*;
import com.github.imran.taskscheduler.models.*;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
        private ArrayList<TaskModel> tasks;

        public CardViewAdapter(ArrayList<TaskModel> tasks) {
            this.tasks = tasks;
        }

        @Override
        public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardViewAdapter.ViewHolder viewHolder, int i) {
            viewHolder.task_title.setText(tasks.get(i).getTaskTitle());
            viewHolder.task_details.setText(tasks.get(i).getTaskDetails());
            viewHolder.task_date.setText(tasks.get(i).getTaskDate());
            viewHolder.task_time.setText(tasks.get(i).getTaskTime());
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView task_title;
            private TextView task_details;
            private TextView task_date;
            private TextView task_time;

            public ViewHolder(View view) {
                super(view);
                task_title = (TextView)view.findViewById(R.id.task_title);
                task_details = (TextView)view.findViewById(R.id.task_details);
                task_date = (TextView)view.findViewById(R.id.task_date);
                task_time = (TextView)view.findViewById(R.id.task_time);
            }
        }
}
