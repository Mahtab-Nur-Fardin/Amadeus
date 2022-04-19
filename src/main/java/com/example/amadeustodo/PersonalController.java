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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.util.Callback;
public class PersonalController implements Initializable {


    public static ObservableList<Tasks> tasksList = FXCollections.observableArrayList();

    @FXML
    private Button addButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label helloLabel;

    @FXML
    private Label noTaskLabel;

    @FXML
    private Button taskButton;

    @FXML
    private Button notesButton;

    @FXML
    private Button notificationsButton;

    @FXML
    private ListView<Tasks> personalTaskList;


    @FXML
    void addButtonsPressed(ActionEvent event) throws IOException {
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
    void taskButtonPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage stage = (Stage)taskButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    };

    @FXML
    void notesButtonPressed(ActionEvent event) {

    }

    @FXML
    void notificationsButtonsPressed(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        personalTaskList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tasks>() {
            @Override
            public void changed(ObservableValue<? extends Tasks> observableValue, Tasks tasks, Tasks t1) {
                String title = personalTaskList.getSelectionModel().getSelectedItem().getTaskTitle();
                String desc = personalTaskList.getSelectionModel().getSelectedItem().getTaskDesc();
                int id = personalTaskList.getSelectionModel().getSelectedItem().getTaskID();
                LocalDate deadline = personalTaskList.getSelectionModel().getSelectedItem().getDeadline();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskDetails.fxml"));
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
                TaskDetailsController taskDetailsController = (TaskDetailsController) fxmlLoader.getController();
                taskDetailsController.setTitleText(title);
                taskDetailsController.setDescText(desc);
                taskDetailsController.setDeadline(deadline);
                taskDetailsController.setTaskid(id);
            }
        });

    }

    public void showTasks() throws Exception {
        ObservableList<Tasks> list = jdbcDbObj.showPersonalTaskList();

        personalTaskList.setCellFactory(lv -> new ListCell<Tasks>() {
            @Override
            public void updateItem(Tasks item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text = item.getTaskTitle(); ; // get text from item
                    setText(text);
                }
            }
        });
        if(tasksList.isEmpty())noTaskLabel.setText("No Tasks for Today!!");
        personalTaskList.setItems(list);
    }

    private ObservableList<Tasks> getTaskList() throws Exception {
        ObservableList<Tasks> tasksList = FXCollections.observableArrayList();

        return tasksList;
    }
}