package com.github.imran.taskscheduler.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME="taskscheduler";
    static final int DATABASE_VERSION=1;
    static final String TABLE_NAME ="tasklist";


    static final String COL_ID="id";
    static final String COL_TITLE="taskTitle";
    static final String COL_DETAILS="taskDetails";
    static final String COL_DATE="taskDate";
    static final String COL_TIME="taskTime";

    static final String CREATE_TABLE="create table "+ TABLE_NAME +"( " +
            COL_ID+" integer primary key, " +
            COL_TITLE+" text, " +
            COL_DETAILS+" text, " +
            COL_DATE+" text, "+
            COL_TIME+" text);";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist "+ TABLE_NAME);
        onCreate(db);
    }
}