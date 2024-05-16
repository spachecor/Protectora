module org.project.protectora.protectoraproyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.jdt.annotation;
    requires java.desktop;
    requires javafx.swing;
    requires java.logging;
    requires java.sql;


    opens org.project.protectora to javafx.fxml;
    exports org.project.protectora;
    exports org.project.protectora.controllers;
    opens org.project.protectora.controllers to javafx.fxml;
}