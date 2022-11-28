module com.example.psp_ud2_act {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.psp_ud2_act6 to javafx.fxml;
    exports com.example.psp_ud2_act6;
}