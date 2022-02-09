module com.mathtrainer.mathtrainer {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires spark.core;
    requires dotenv.java;
    requires org.postgresql.jdbc;
    requires java.sql;

    exports client;
    opens client to javafx.fxml;
    exports client.controllers;
    opens client.controllers to javafx.fxml;
    exports model;
    opens model to com.google.gson;
}