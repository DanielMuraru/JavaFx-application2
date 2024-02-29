module com.example.ofertev {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    opens com.example.ofertev.domain to javafx.base;
    opens com.example.ofertev.controller to javafx.fxml;
    opens com.example.ofertev.dtos to javafx.base;
    opens com.example.ofertev to javafx.fxml;
    exports com.example.ofertev;
    exports com.example.ofertev.controller;
}