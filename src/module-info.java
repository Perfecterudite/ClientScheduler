module ClientScheduler {
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Company;
    opens Company.Controller;
    opens Company.View;
    opens Company.Model;
}