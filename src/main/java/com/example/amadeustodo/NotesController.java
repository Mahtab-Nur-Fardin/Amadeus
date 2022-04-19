package com.example.amadeustodo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NotesController implements Initializable {

    public static ObservableList<Notes> notesList = FXCollections.observableArrayList();

    @FXML
    private Button addButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label helloLabel;

    @FXML
    private ListView<Notes> notesListView;

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
    void notesButtonPressed(ActionEvent event) {

    }

    @FXML
    void notificationsButtonsPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Notifications.fxml"));
        Stage stage = (Stage)notificationsButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void taskButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage)taskButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showNotes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        notesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Notes>() {
            @Override
            public void changed(ObservableValue<? extends Notes> observableValue, Notes notes, Notes t1) {
                String title = notesListView.getSelectionModel().getSelectedItem().getNoteTitle();
                String desc = notesListView.getSelectionModel().getSelectedItem().getNoteDescription();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NoteDetails.fxml"));
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
                NoteDetailsController noteDetailsController = (NoteDetailsController) fxmlLoader.getController();
                noteDetailsController.setTitleText(title);
                noteDetailsController.setDescText(desc);
            }
        });
    }
    public void showNotes() throws Exception {
        ObservableList<Notes> list = jdbcDbObj.showNotesList();

        notesListView.setItems(list);
        notesListView.setCellFactory(lv -> new ListCell<Notes>() {
            @Override
            public void updateItem(Notes item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text = item.getNoteTitle(); ; // get text from item
                    setText(text);
                }
            }
        });

    }

    private ObservableList<Notes> getNoteList()
    {
        return notesList;
    }
}
