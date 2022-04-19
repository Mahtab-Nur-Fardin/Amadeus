package com.example.amadeustodo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NoteDetailsController {

    @FXML
    private Label noteDescLabel;

    @FXML
    private Label noteTitleLabel;


    public void setTitleText(String title)
    {
        noteTitleLabel.setText(title);
    }

    public void setDescText(String desc)
    {
        noteDescLabel.setText(desc);
    }

}
