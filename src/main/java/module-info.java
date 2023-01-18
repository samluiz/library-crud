module org.ifpi.bibliotecaif {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;


    opens com.saurs.library to javafx.fxml, javafx.base;
    opens com.saurs.library.model.entities to javafx.base;
    exports com.saurs.library;
}