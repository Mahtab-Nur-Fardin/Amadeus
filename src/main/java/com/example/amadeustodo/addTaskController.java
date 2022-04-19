package com.example.amadeustodo;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class addTaskController implements Initializable {

    @FXML
    private ChoiceBox<Category> categoriesChoicebox;

    @FXML
    private DatePicker Deadline;

    @FXML
    private ImageView Exit;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private TextArea taskDescription;

    @FXML
    private TextField taskTitle;

    public static Category taskCategory;


    @FXML
    void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void okButtonPressed(ActionEvent event) throws Exception {
        String title = taskTitle.getText();
        Category category = categoriesChoicebox.getValue();
        taskCategory = category;
        String desc = taskDescription.getText();
        LocalDate deadline = Deadline.getValue();
        Tasks task = new Tasks(category, title, desc, deadline);

        jdbcDbObj.addNewTask(event, title, desc, deadline, category);

        // TODO: 4/18/2022 ADD DATABASE CODE

        if(task.getCategory() == Category.Personal) PersonalController.tasksList.add(task);
        else WorkController.tasksList.add(task);


        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
        //jdbcDbObj.showPersonalTaskList();
    }

    private Category category = Category.Personal;

    public Category getCategory() {
        return category;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategory();
    }

    private void setCategory() {
        categoriesChoicebox.setItems( FXCollections.observableArrayList( Category.values()));
//        categoriesChoicebox.setValue(category);
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setCategory();
//        okButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if(!taskTitle.getText().trim().isEmpty() && !taskDescription.getText().trim().isEmpty() ){
//                    jdbcDbObj.addNewTask(event, taskTitle.getText(), taskDescription.getText(),Deadline.getValue(),taskCategory);
//
//                }else {
//                    System.out.println("Please fill in all information");
//                    Alert alert = new Alert((Alert.AlertType.ERROR));
//                    alert.setContentText("Please fill in all information to sign up!");
//                    alert.show();
//                }
//            }
//        });
//    }

}
