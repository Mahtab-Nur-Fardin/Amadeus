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

public class NotificationsController {

    @FXML
    private Button addButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label helloLabel;

    @FXML
    private Button notesButton;

    @FXML
    private Button notificationsButton;

    @FXML
    private Button taskButton;

    @FXML
    void addButtonsPressed(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddNew.fxml"));
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
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void closeButtonPressed(ActionEvent event) {
        closeButton.setOnAction((ActionEvent e)->{
            System.exit(0);
        });
    }

    @FXML
    void notesButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Notes.fxml"));
        Stage stage = (Stage)notesButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void notificationsButtonsPressed(ActionEvent event) {

    }

    @FXML
    void taskButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage)taskButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
