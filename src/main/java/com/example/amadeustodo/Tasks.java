package com.example.amadeustodo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tasks {
    public static void setTaskID(int taskID) {
        TaskID = taskID;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    private static int TaskID;
    private Category category;
    private String taskTitle;
    private String taskDesc;
    private LocalDate deadline;

    public static int getTaskID() {
        return TaskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDeadline() {
        return deadline;
    }


    public Tasks(Category category, String taskTitle, String taskDesc, LocalDate deadline) {
        //TaskID++;
        this.category = category;
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }
    public Tasks(int taskID,Category category, String taskTitle, String taskDesc, LocalDate deadline) {
        TaskID = taskID;
        this.category = category;
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }
    public static LocalDate dateInput(String userInput) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        System.out.println(date);
        return date ;
    }
}
