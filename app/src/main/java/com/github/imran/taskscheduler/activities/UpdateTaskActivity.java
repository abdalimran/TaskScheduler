package com.github.imran.taskscheduler.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.imran.taskscheduler.R;
import com.github.imran.taskscheduler.database.DBOperations;
import com.github.imran.taskscheduler.fragments.DatePickerFragment;
import com.github.imran.taskscheduler.fragments.TimePickerFragment;
import com.github.imran.taskscheduler.models.TaskModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateTaskActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener,TimePickerFragment.TimeDialogListener{

    private static final String DIALOG_DATE = "MainActivity.DateDialog";
    private static final String DIALOG_TIME = "MainActivity.TimeDialog";

    DBOperations taskDBOperations;

    private EditText updttaskTitle;
    private EditText updttaskDetails;
    private TextView ushowDate;
    private TextView ushowTime;

    private String tDate;
    private String tTime;
    private int taskID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        setTitle("Update & Delete Task");

        taskDBOperations=new DBOperations(this);

        updttaskTitle= (EditText) findViewById(R.id.updt_tasktitle);
        updttaskDetails= (EditText) findViewById(R.id.updt_taskdetails);
        ushowDate= (TextView) findViewById(R.id.ushowDate);
        ushowTime= (TextView) findViewById(R.id.ushowTime);

        Bundle tID = getIntent().getExtras();
        taskID = tID.getInt("id");

        TaskModel task=taskDBOperations.getTask(taskID);
        updttaskTitle.setText(task.getTaskTitle());
        updttaskDetails.setText(task.getTaskDetails());
        ushowDate.setText(task.getTaskDate());
        ushowTime.setText(task.getTaskTime());

        System.out.println(updttaskTitle);
        System.out.println(updttaskDetails);
        System.out.println(tDate);
        System.out.println(tTime);
    }

    public void updateTask(View view) {

        if(updttaskTitle!=null && updttaskDetails!=null && tDate!=null && tTime!=null){
            String utTitle = updttaskTitle.getText().toString();
            String utDetails = updttaskDetails.getText().toString();

            TaskModel task=new TaskModel(utTitle,utDetails,tDate,tTime);

            taskDBOperations.updateTask(taskID,task);
            Toast.makeText(this,"Task updated sucessfully!",Toast.LENGTH_SHORT).show();
            finish();

        }
        else {
            Toast.makeText(this,"Please fill up every fields correctly!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFinishDialog(Date date) {
        ushowDate.setText(formatDate(date));
        this.tDate=formatDate(date);
    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }

    @Override
    public void onFinishDialog(String time) {
        ushowTime.setText(time);
        this.tTime=time;
    }

    public void upickTime(View view) {
        TimePickerFragment dialog = new TimePickerFragment();
        dialog.show(getSupportFragmentManager(), DIALOG_TIME);
    }

    public void upickDate(View view) {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), DIALOG_DATE);
    }

}
