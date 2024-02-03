open module ma.enset.examenjdbcfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

   // opens ma.enset.examenjdbcfx to javafx.fxml;
    exports ma.enset.examenjdbcfx.presentation;
    //exports ma.enset.examenjdbcfx.presentation;
    //opens ma.enset.examenjdbcfx.presentation to javafx.fxml;
    exports ma.enset.examenjdbcfx;
    exports ma.enset.examenjdbcfx.presentation.controller;
    exports ma.enset.examenjdbcfx.service;

    // opens ma.enset.examenjdbcfx.presentation.controller to javafx.fxml;
}