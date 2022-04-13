package client;

import client.controllers.MainController;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class SceneSwitchTest extends ApplicationTest {
    MainController mainController;
    Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainController = new MainController(stage);
    }

    @Test
    void testExercisesScene() {

    }

    @Test
    void testGameScene() {

    }

    @Test
    void testHomeScene() {

    }

    @Test
    void testLogInScene() {

    }

    @Test
    void testNewUserScene() {

    }

    //5nis
    @Test
    void testQuizScene() {

    }

    @Test
    void testQuizCompletedScene() {

    }

    @Test
    void testResultsScene() {

    }

    @Test
    void testSettingsScene() {

    }

    @Test
    void testStartGameScene() {

    }
}
