package com.example.amadeustodo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AddNewController {

    @FXML
    private Button addNoteButton;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button closeButton;

    @FXML
    void addNoteButtonPressed(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addNote.fxml"));
        Parent root;
        try{
            root = fxmlLoader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addTaskButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addTask.fxml"));
        Parent root;
        try{
            root = fxmlLoader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
//        Parent root = FXMLLoader.load(getClass().getResource("addTask.fxml"));
//        Stage stage = (Stage)addTaskButton.getScene().getWindow();
//        stage.setScene(new Scene(root));
    }

    @FXML
    void closeButtonPressed(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}