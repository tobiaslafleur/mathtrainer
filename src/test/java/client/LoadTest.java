package client;

import client.controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;

/**
 * Test class that tests if a fxml document loads correctly and initialize the correct controller
 * @author Duy Nguyen
 */
public class LoadTest extends ApplicationTest {
    MainController mainController;
    Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainController = new MainController(stage);
    }

    @Test
    void testExercisesScene() throws IOException {
        URL location = getClass().getResource("/fxml/Exercises.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.load();
        assertInstanceOf(ExercisesController.class, fxmlLoader.getController()); //Check if it is the correct controller

        ExercisesController exercisesController = fxmlLoader.getController();
        assertEquals(location, exercisesController.getLocation()); //Check if it is the same location/file
    }

    @Test
    void testGameScene() throws IOException {
        URL location = getClass().getResource("/fxml/GameScene.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.load();
        assertInstanceOf(GameController.class, fxmlLoader.getController());

        GameController gameController = fxmlLoader.getController();
        assertEquals(location, gameController.getLocation());
    }

    @Test
    void testHomeScene() throws IOException {
        URL location = getClass().getResource("/fxml/Home.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.load();
        assertInstanceOf(HomeController.class, fxmlLoader.getController());

        HomeController homeController = fxmlLoader.getController();
        assertEquals(location, homeController.getLocation());
    }

    @Test
    void testLogInScene() throws IOException {
        URL location = getClass().getResource("/fxml/LogIn.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.load();
        assertInstanceOf(LogInController.class, fxmlLoader.getController());

        LogInController logInController = fxmlLoader.getController();
        assertEquals(location, logInController.getLocation());
    }

    @Test
    void testNewUserScene() throws IOException {
        URL location = getClass().getResource("/fxml/NewUser.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        fxmlLoader.load();
        assertInstanceOf(NewUserController.class, fxmlLoader.getController());

        NewUserController newUserController = fxmlLoader.getController();
        assertEquals(location, newUserController.getLocation());
    }

    //5nis
    @Disabled
    @Test
    void testQuizScene() {

    }
    @Disabled
    @Test
    void testQuizCompletedScene() {

    }
    @Disabled
    @Test
    void testResultsScene() {

    }
    @Disabled
    @Test
    void testSettingsScene() {

    }
    @Disabled
    @Test
    void testStartGameScene() {

    }
}
