module com.example.smartdeliverysystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.smartdeliverysystem to javafx.fxml;
    exports com.example.smartdeliverysystem;
}