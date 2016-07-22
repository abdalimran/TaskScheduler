package com.github.imran.taskscheduler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.github.imran.taskscheduler.models.TaskModel;

import java.util.ArrayList;

public class DBOperations {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private TaskModel tasks;

    public DBOperations(Context context){
        databaseHelper=new DatabaseHelper(context);
        tasks=new TaskModel();
    }

    public void open(){
        db=databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
    }

    public boolean addTask(TaskModel tasks){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_TITLE,tasks.getTaskTitle());
        contentValues.put(DatabaseHelper.COL_DETAILS,tasks.getTaskDetails());
        contentValues.put(DatabaseHelper.COL_DATE,tasks.getTaskDate());
        contentValues.put(DatabaseHelper.COL_TIME,tasks.getTaskTime());

        long inserted=db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

        this.close();

        if(inserted>0)
            return true;
        else
            return false;
    }

    public TaskModel getTask(int id){

        this.open();

        Cursor cursor=db.query(
                DatabaseHelper.TABLE_NAME,
                new String[]{
                        DatabaseHelper.COL_ID,
                        DatabaseHelper.COL_TITLE,
                        DatabaseHelper.COL_DETAILS,
                        DatabaseHelper.COL_DATE,
                        DatabaseHelper.COL_TIME},

                DatabaseHelper.COL_ID+" = "+id,
                null,null,null,null);

        if(cursor!=null && cursor.getCount()>0) {
            cursor.moveToFirst();

            int tID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
            String tTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TITLE));
            String tDetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DETAILS));
            String tDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DATE));
            String tTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));
            cursor.close();
            tasks = new TaskModel(tID,tTitle,tDetails,tDate,tTime);
        }

        this.close();

        return tasks;
    }

    public ArrayList<TaskModel>getAllTasks(){
        this.open();
        ArrayList<TaskModel>allTasks=new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from "+DatabaseHelper.TABLE_NAME,null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();

            for(int i=0;i<cursor.getCount();i++){
                int tID=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String tTitle=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TITLE));
                String tDetails=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DETAILS));
                String tDate=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DATE));
                String tTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));

                tasks = new TaskModel(tID,tTitle,tDetails,tDate,tTime);
                allTasks.add(tasks);

                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return allTasks;
    }

    public boolean updateTask(int id,TaskModel task){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_TITLE,task.getTaskTitle());
        contentValues.put(DatabaseHelper.COL_DETAILS,task.getTaskDetails());
        contentValues.put(DatabaseHelper.COL_DATE,task.getTaskDate());
        contentValues.put(DatabaseHelper.COL_TIME,task.getTaskTime());

        int update=db.update(DatabaseHelper.TABLE_NAME,contentValues,
                             DatabaseHelper.COL_ID+ " = "+id,null);
        this.close();

        if(update>0)
            return  true;
        else
            return false;
    }

    public boolean deleteTask(int id){
        this.open();
        int deleted=db.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.COL_ID+" = "+id,null);
        this.close();

        if(deleted>0)
            return true;
        else
            return false;
    }

}