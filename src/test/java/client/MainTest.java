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
import java.io.IOException;

class MainTest extends ApplicationTest{
    MainController mainController;
    @Override
    public void start(Stage stage) throws IOException {
        mainController = new MainController(new Stage());
    }

    @Test
    void logIn() {
        this.clickOn("#usernameField").write("asdasd");
        this.clickOn("#passwordField").write("asdasd");
        this.clickOn("#LoggaIn");
    }

    @Test
    void skipLogIn() {

    }

}