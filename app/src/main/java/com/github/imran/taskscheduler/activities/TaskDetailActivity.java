package com.github.imran.taskscheduler.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.imran.taskscheduler.R;
import com.github.imran.taskscheduler.database.DBOperations;
import com.github.imran.taskscheduler.models.TaskModel;

public class TaskDetailActivity extends AppCompatActivity {

    DBOperations taskDBOperations;
    private TextView shwtaskTitle;
    private TextView shwtaskDetails;
    private TextView shwtaskDate;
    private TextView shwtaskTime;
    private int taskID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        setTitle("Task Details");
    }

    private void initViews() {
        taskDBOperations=new DBOperations(this);

        shwtaskTitle= (TextView) findViewById(R.id.shw_tasktitle);
        shwtaskDetails= (TextView) findViewById(R.id.shw_taskdetails);
        shwtaskDate= (TextView) findViewById(R.id.shw_taskdate);
        shwtaskTime= (TextView) findViewById(R.id.shw_tasktime);

        Bundle tID = getIntent().getExtras();
        taskID = tID.getInt("id");

        TaskModel task=taskDBOperations.getTask(taskID);
        shwtaskTitle.setText(task.getTaskTitle());
        shwtaskDetails.setText(task.getTaskDetails());
        shwtaskDate.setText("Date: "+task.getTaskDate());
        shwtaskTime.setText("Time: "+task.getTaskTime());
    }

    public void editTask(View view) {
        Intent intent = new Intent(getApplicationContext(), UpdateTaskActivity.class);
        intent.putExtra("id",taskID);
        startActivity(intent);
        finish();
    }

    public void deleteTask(View view) {
        taskDBOperations.deleteTask(taskID);
        Intent intent = new Intent (this, MainActivity.class);
        startActivity (intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
        Log.d("TAG: "+this.getLocalClassName().toString(),"onStart");
    }
}
