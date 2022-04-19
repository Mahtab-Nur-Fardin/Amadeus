package com.example.amadeustodo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController {

    @FXML
    private Label helloLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button notesButton;

    @FXML
    private Button notificationsButton;

    @FXML
    private Button personalButton;

    @FXML
    private Button workButton;

    @FXML
    void addButtonsPressed(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("AddNew.fxml"));
//        Stage stage = (Stage)addButton.getScene().getWindow();
//        stage.setScene(new Scene(root));
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
    void taskButtonPressed(ActionEvent event) {

    }

    @FXML
    void notesButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Notes.fxml"));
        Stage stage = (Stage)notesButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void notificationsButtonsPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Notifications.fxml"));
        Stage stage = (Stage)notificationsButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void personalButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Personal.fxml"));
        Stage stage = (Stage)personalButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void workButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Work.fxml"));
        Stage stage = (Stage)workButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void setTextHelloLabel(String username)
    {
        helloLabel.setText("Hello "+username);
    }


}
