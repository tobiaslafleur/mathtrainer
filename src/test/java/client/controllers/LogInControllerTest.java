package client.controllers;

import client.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;
import java.io.IOException;

class LogInControllerTest extends ApplicationTest {
    private MainController mainController;
    private LogInController lc;
    @Override
    public void start(Stage stage) throws IOException {
        Stage stage1 = new Stage();
        mainController = new MainController(stage1);
        FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        // Parent root = logInLoader.load();

        Scene logInScene = new Scene(logInLoader.load());
        lc = logInLoader.getController();
        stage.setScene(logInScene);
        stage.show();
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