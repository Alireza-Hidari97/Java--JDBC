module com.example.jdbcfxexample_comp228_014 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.sql.rowset;

    opens com.example.jdbcfxexample_comp228_014 to javafx.fxml;
    exports com.example.jdbcfxexample_comp228_014;
}