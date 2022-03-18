package client;

import static org.junit.jupiter.api.Assertions.*;
import client.Main;
import client.controllers.LogInController;
import client.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;

import java.awt.*;
import java.io.IOException;

class MainTest extends ApplicationTest{
    MainController mainController;
    Stage stage;
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainController = new MainController(stage);
    }

    @Test
    void logIn() throws IOException {

/*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();

        this.clickOn("#usernameField").write("asdasd");
        this.clickOn("#passwordField").write("asdasd");

        assertEquals("Success", logInController.logInClicked());

        // this.clickOn("#LoggaIn");
        */

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();
        logInController.setUserPassword("asdasd", "asdasd");
        assertEquals("Failed", logInController.logInClicked());

    }

    @Test
    void skipLogIn() {

    }

}