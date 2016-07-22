package com.github.imran.taskscheduler.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AddTaskActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener,TimePickerFragment.TimeDialogListener{
    private static final String DIALOG_DATE = "MainActivity.DateDialog";
    private static final String DIALOG_TIME = "MainActivity.TimeDialog";

    DBOperations taskDBOperations;

    private EditText taskTitle;
    private EditText taskDetails;
    private TextView showDate;
    private TextView showTime;

    private String tDate;
    private String tTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskDBOperations=new DBOperations(this);

        taskTitle= (EditText) findViewById(R.id.edt_tasktitle);
        taskDetails= (EditText) findViewById(R.id.edt_taskdetails);
        showDate= (TextView) findViewById(R.id.showDate);
        showTime= (TextView) findViewById(R.id.showTime);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addTask(View view) {

        if(taskTitle!=null && taskDetails!=null && tDate!=null && tTime!=null){
            String tTitle = taskTitle.getText().toString();
            String tDetails = taskDetails.getText().toString();

            TaskModel task=new TaskModel(tTitle,tDetails,tDate,tTime);

            taskDBOperations.addTask(task);

            Toast.makeText(this,"Task added sucessfully!",Toast.LENGTH_SHORT).show();

            finish();
        }
        else {
            Toast.makeText(this,"Please fill up every fields correctly!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFinishDialog(Date date) {
        showDate.setText(formatDate(date));
        this.tDate=formatDate(date);
    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }

    @Override
    public void onFinishDialog(String time) {
        showTime.setText(time);
        this.tTime=time;
    }

    public void pickTime(View view) {
        TimePickerFragment dialog = new TimePickerFragment();
        dialog.show(getSupportFragmentManager(), DIALOG_TIME);
    }

    public void pickDate(View view) {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), DIALOG_DATE);
    }
}
