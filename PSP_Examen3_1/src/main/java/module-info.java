module com.example.psp_examen3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.psp_examen3_1 to javafx.fxml;
    exports com.example.psp_examen3_1;
}