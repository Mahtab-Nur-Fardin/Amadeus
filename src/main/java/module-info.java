module com.example.amadeustodo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.amadeustodo to javafx.fxml;
    exports com.example.amadeustodo;
}