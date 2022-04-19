package com.example.amadeustodo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class addNoteController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextArea noteDescription;

    @FXML
    private TextField noteTitle;

    @FXML
    private Button okButton;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void closeButtonPressed(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void okButtonPressed(ActionEvent event) {
        String title = noteTitle.getText();
        String desc = noteDescription.getText();
        Notes notes = new Notes(title, desc);
        NotesController.notesList.add(notes);
        jdbcDbObj.addNewNotes(event,title,desc);
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }



}
