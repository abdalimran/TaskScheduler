package com.github.imran.taskscheduler.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    }

    public void deleteTask(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        taskDBOperations.deleteTask(taskID);
                        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                        startActivity (intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
    }
}
