package com.github.imran.taskscheduler.models;

public class TaskModel {
    private int taskID;
    private String taskTitle;
    private String taskDetails;
    private String taskDate;
    private String taskTime;

    public TaskModel(int taskID, String taskTitle, String taskDetails, String taskDate, String taskTime) {
        this.taskID = taskID;
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

    public TaskModel(String taskTitle, String taskDetails, String taskDate, String taskTime) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

    public TaskModel() {

    }

    public int getTaskID() {
        return taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }
}
