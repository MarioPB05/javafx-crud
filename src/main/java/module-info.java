module mpb.control_de_personas {
    requires javafx.controls;
    requires javafx.fxml;


    opens controller to javafx.fxml;
    opens model to javafx.base;
    exports controller;
}