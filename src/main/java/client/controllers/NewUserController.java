package client.controllers;

import client.entity.ScenesEnum;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import model.User;

import java.util.regex.Pattern;

/**
 * Controller for handling button-presses in the scene NewUser.fxml. Each method represent a possible user action.
 * @author Niklas Hultin
 * @version 1.0
 */

public class NewUserController extends SceneControllerParent implements InitializeSceneInterface {
    @FXML
    TextField username;
    @FXML
    PasswordField password, passwordRepeat;
    @FXML
    ChoiceBox<String> year;

    private final int MIN_NAME_LENGTH = 4;
    private final int MAX_NAME_LENGTH = 20;
    private final int MIN_PASSWORD_LENGTH = 6;

    @Override
    public void setInitialValues(Object object) {
        username.setText("");
        password.setText("");
        passwordRepeat.setText("");
        year.getSelectionModel().select(0);
    }

    /**
     * Returns to the login scene if the user backs out of creating a new user.
     */
    public void backClicked(){
        mainController.setScene(ScenesEnum.LogIn);
    }

    public void createUserClicked() {
        String trimmedUsername = username.getText().trim();

        if (isValidUsername(trimmedUsername) && isValidPassword(password.getText(), passwordRepeat.getText())) {
            User user = new User(trimmedUsername, password.getText(), false);
            user.setYear(Integer.parseInt(year.getValue()));

            HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/user").body(new Gson().toJson(user)).asJson();

            if (response.getStatus() == 200) {
                popUpWindow("Klar", "Ett konto har skapats", Alert.AlertType.INFORMATION);
                mainController.setScene(ScenesEnum.LogIn);
            }
            else {
                popUpWindow("Fel", "Det gick ej att skapa kontot");
            }
        }
    }

    public boolean isValidUsername(String username){
        username = username.trim();

        if (username.length() < MIN_NAME_LENGTH || username.length() > MAX_NAME_LENGTH) {
            popUpWindow("Användarnamnet är för kort eller långt", "Användarnamnet måste vara mellan 5 och 20 tecken långt");
            return false;
        } else if(!Pattern.compile("^[a-zA-Z0-9]+$").matcher(username).matches()){
            popUpWindow("Specialtecken är ej tillåtna", "Användarnamnet får ej innehålla specialtecken, som t ex !, &, *");
            return false;
        }

        return true;
    }

    public Boolean isValidPassword(String password, String passwordRepeat){
        if (!password.equals(passwordRepeat)) {
            popUpWindow("Felaktigt lösenord", "Lösenorden du angav stämmer ej överens med varandra");
            return false;
        } else if (password.length() < MIN_PASSWORD_LENGTH){
            popUpWindow("Lösenordet är för kort", "Lösenordet måste vara minst 6 tecken långt");
            return false;
        }

        return true;
    }

    private void popUpWindow(String title, String message){
        popUpWindow(title, message, Alert.AlertType.ERROR);
    }

    private void popUpWindow(String title, String message, AlertType alertType) {
        if (mainController != null){
            mainController.popUpWindow(alertType, title, message);
        }
    }
}
