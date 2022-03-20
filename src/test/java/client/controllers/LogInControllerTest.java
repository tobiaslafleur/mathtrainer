package client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;
import java.io.IOException;

/**
 * Tests for logging in.
 * @author Ludvig Wedin Pettersson
 */
class LogInControllerTest extends ApplicationTest {
    private MainController mainController;
    private Stage stage;
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainController = new MainController(stage);
    }

    @Test
    void logInCorrect() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();
        logInController.setUserPassword("asdasd", "asdasd");
        assertEquals("Success", logInController.logInClicked());
    }

    @Test
    void logInWrongPw() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();
        logInController.setUserPassword("asdasd", "asdasdasd");
        assertEquals("Failed: bad info", logInController.logInClicked());
    }

    @Test
    void logInWrongUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();
        logInController.setUserPassword("asdasdasd", "asdasd");
        assertEquals("Failed: bad info", logInController.logInClicked());
    }

    @Test
    void logInMissingUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();
        logInController.setUserPassword("", "asdasd");
        assertEquals("Failed: missing user or pw", logInController.logInClicked());
    }

    @Test
    void logInMissingPw() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();
        logInController.setUserPassword("asdasd", "");
        assertEquals("Failed: missing user or pw", logInController.logInClicked());
    }

    @Test
    void logInMissingUserPw() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
        Parent root = loader.load();
        LogInController logInController = loader.getController();
        logInController.setUserPassword("", "");
        assertEquals("Failed: missing user or pw", logInController.logInClicked());
    }

    @Test
    void skipLogIn() {

    }
}