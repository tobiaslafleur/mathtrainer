package client;

import static org.junit.jupiter.api.Assertions.*;

import client.controllers.LogInController;
import client.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.awt.*;
import java.io.IOException;

class MainGUITest extends ApplicationTest{
    MainController mainController;
    Stage stage;
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainController = new MainController(stage);
    }

    @Test
    void usernameInput() {
        this.clickOn("#usernameField").write("asdasd");
        FxAssert.verifyThat("#usernameField", TextInputControlMatchers.hasText("asdasd"));
    }

    @Test
    void passwordInput() {
        this.clickOn("#passwordField").write("asdasd");
        FxAssert.verifyThat("#passwordField", TextInputControlMatchers.hasText("asdasd"));
    }

    @Test
    void skipLogIn() {

    }

}