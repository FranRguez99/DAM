module safa.fran.ad_proyectohospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens safa.fran.ad_proyectohospital to javafx.fxml;
    exports safa.fran.ad_proyectohospital;
}