module com.example.screenreader {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;


    opens be.kdg.tic_tac_toe to javafx.fxml;
    exports be.kdg.tic_tac_toe;
}