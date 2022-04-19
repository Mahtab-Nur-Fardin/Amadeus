package com.example.amadeustodo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class TaskDetailsController {

    @FXML
    private Label taskDeadline;

    @FXML
    private Label taskDesc;

    @FXML
    private Label taskTitle;

    @FXML
    private Button closeButton;

    @FXML
    private Button updateBtn;

    public static int Taskid;
    public static String username;
    public static String ttle;
    public static String Desc;
    public static String catgry;
    public static LocalDate dedline;



    @FXML
    void deleteButtonPressed(ActionEvent event) throws Exception {

        jdbcDbObj.deletetask(event,Taskid);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
//        Parent root = fxmlLoader.load();
//        Stage stage = (Stage)closeButton.getScene().getWindow();
//        stage.setScene(new Scene(root));
//        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
//        Stage stage = (Stage)updateBtn.getScene().getWindow();
//        stage.setScene(new Scene(root));
    }

    @FXML
    void updateButtonPressed(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("updateTask.fxml"));
        Stage stage = (Stage)updateBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        jdbcDbObj.deletetask(event,Taskid);

    }

    public static   int  getTaskid()
    {
        return Taskid;
    }

    public void setTitleText(String title)
    {
        taskTitle.setText(title);
        ttle =title;
    }
    public void setTaskid(int taskid)
    {
        //taskTitle.setText(title);
        Taskid = taskid;
    }

    public void setDescText(String desc)
    {
        taskDesc.setText(desc);
        Desc = desc;
    }

    public void setDeadline(LocalDate deadline)
    {
        taskDeadline.setText("Deadline: " +String.valueOf(deadline));
        dedline = deadline;
    }


}