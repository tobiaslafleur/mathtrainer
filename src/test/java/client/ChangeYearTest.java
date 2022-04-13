package client;

import client.controllers.MainController;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that creates a new user, logs the new user in and tests the changing of the user's year
 * @author Duy Nguyen
 * @author Hanis Saley
 */
public class ChangeYearTest extends ApplicationTest {
    MainController mainController;
    Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainController = new MainController(stage);
    }

    @Test
    void testChangeYear() {
        //Create user
        clickOn("#NyAnvändare");
        clickOn("#username").write("changeYearTest");
        clickOn("#password").write("random");
        clickOn("#passwordRepeat").write("random");
        clickOn("#year").clickOn("6");
        clickOn("#SkapaAnvändare");
        clickOn("OK");

        //Log user in
        clickOn("#usernameField").write("changeYearTest");
        clickOn("#passwordField").write("random");
        clickOn("#LoggaIn");

        //Change year
        clickOn("#Inställningar");
        clickOn("#year").clickOn("9");
        clickOn("#Byt");
        assertEquals(9, mainController.getCurrentUser().getYear());
    }
}
