package client.controllers;

import client.entity.ScenesEnum;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import model.User;

import java.util.HashMap;

/**
 * Controller for handling button-presses in the scene LogIn.fxml. Each method represent a possible user action.
 * @author Niklas Hultin
 * @version 1.0
 */

public class LogInController extends SceneControllerParent implements InitializeSceneInterface {


    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    /**
     * Called when the user skips the log in. Confirms with the user if they are sure, and if so skips the login phase.
     */
    public void skipLogInClicked() {
        boolean answer = mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Fortsätt utan att logga in?", "Om du inte loggar in eller skapar en användare kommer ingenting att sparas. " +
                "Är du säker på att du vill fortsätta utan att logga in?");
        if (answer){
            //ToDo: Kod för att spela som gäst
            mainController.skipLogin();
        }
    }

    /**
     * Called when the user clicks log in. Gets the user information and sends it to the main controller.
     */
    public String logInClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            if (mainController != null)
                mainController.popUpWindow(Alert.AlertType.ERROR, "Felaktiga användaruppgifter", "Du måste fylla i både användarnamn och lösenord");
            return "Failed: missing user or pw";
        }
        else {
            User user = new User(username, password, false);
            HttpResponse<JsonNode> loginResponse = Unirest.post("http://localhost:5000/login").body(new Gson().toJson(user)).asJson();
            JSONObject responseMap = loginResponse.getBody().getObject();

            if (responseMap.get("response").toString().contains("Credentials")) {
                if (mainController != null)
                    mainController.popUpWindow(Alert.AlertType.ERROR, "Felaktiga användaruppgifter", "Du har angett fel användarnamn eller lösenord");
                return "Failed: bad info";
            }
            else {
                String id = responseMap.get("response").toString();
                HttpResponse<JsonNode> getResponse = Unirest.get("http://localhost:5000/user/" + Integer.parseInt(id)).asJson();
                user = new Gson().fromJson(String.valueOf(getResponse.getBody()), User.class);

//                HttpResponse<JsonNode> rs = Unirest.get("http://localhost:5000/user/").asJson();
//                System.out.println(rs.getStatus());

                if (mainController != null) {
                    mainController.setScene(ScenesEnum.Home);
                    mainController.setCurrentUser(user);
                    mainController.setInitialValueOfScene(user);
                }
                return "Success";
            }
        }
    }

    /**
     * Called when the user chooses to create a new user.
     */
    public void newUserClicked() {
        mainController.createNewUser();
    }

    /**
     * Called if the user wants to exit the program. MainController handles this call manually.
     */
    public void exitClicked() {
        mainController.closeProgram();
    }

    public void setUserPassword(String user, String  password) {
        usernameField.setText(user);
        passwordField.setText(password);
    }

    @Override
    public void setInitialValues(Object object) {
        usernameField.setText("");
        passwordField.setText("");
    }
}
