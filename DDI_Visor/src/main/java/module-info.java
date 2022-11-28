module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires jasperreports;

    exports com.controller;
    opens com.controller to javafx.fxml;
}