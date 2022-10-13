module com.example.projektsystemobslugi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.projektsystemobslugi to javafx.fxml;
    exports com.example.projektsystemobslugi;
}