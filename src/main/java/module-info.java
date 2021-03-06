module com.example.pekversicherung {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens versicherung to javafx.fxml;
    opens versicherung.Models to javafx.base;
    exports versicherung;
    exports versicherung.Controllers;
    opens versicherung.Controllers to javafx.fxml;

    requires java.sql;
}