package client;

import static org.testfx.matcher.control.LabeledMatchers.hasText;
import client.controllers.MainController;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;

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
    void logIn() {
        this.clickOn("#usernameField").write("asdasd");
        this.clickOn("#passwordField").write("asdasd");
        this.clickOn("#LoggaIn");
        FxAssert.verifyThat("#welcomeLabel", hasText("Välkommen asdasd"));
    }

    @Test
    void skipLogIn() {
        this.clickOn("#SkipLogIn");
        this.clickOn("OK");
        FxAssert.verifyThat("#welcomeLabel", hasText("Välkommen"));
    }

    @Test
    void newUser() {
        this.clickOn("#NyAnvändare");
        this.clickOn("#username").write("asdasdasd");
        this.clickOn("#password").write("asdasdasd");
        this.clickOn("#passwordRepeat").write("asdasdasd");
        this.clickOn("#year").clickOn("8");
        this.clickOn("#SkapaAnvändare");
        this.clickOn("OK");
        FxAssert.verifyThat("#SkipLogIn", hasText("Fortsätt utan att logga in"));

    }

}
