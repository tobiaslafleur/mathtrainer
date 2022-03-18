package client.controllers;

import client.entity.ScenesEnum;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import model.NewUser;

import java.util.regex.Matcher;
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
        String trimmedUsername = this.username.getText().trim();

        if (isValidUsername(trimmedUsername) && isValidPassword(password.getText(), passwordRepeat.getText())) {
            NewUser user = new NewUser(trimmedUsername, password.getText(), false);
            user.setYear(Integer.parseInt(year.getValue()));

            HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/user").body(new Gson().toJson(user)).asJson();

            if (response.getStatus() == 200) {
                mainController.popUpWindow(Alert.AlertType.INFORMATION, "Klar", "Ett konto har skapats");
                mainController.setScene(ScenesEnum.LogIn);
            }
            else {
                mainController.popUpWindow(Alert.AlertType.ERROR, "Fel", "Det gick ej att skapa kontot");
            }
        }
    }

    public void createUser(String username, String password, String passwordRepeat, String year){
        this.username.setText(username);
        this.password.setText(password);
        this.passwordRepeat.setText(passwordRepeat);
        this.year.setValue(year);
    }

    public boolean isValidUsername(String username){
        if (username.length() < MIN_NAME_LENGTH || username.length() > MAX_NAME_LENGTH) {
            mainController.popUpWindow(Alert.AlertType.ERROR, "Användarnamnet är för kort eller långt", "Användarnamnet måste vara mellan 5 och 20 tecken långt");
            return false;
        } else if(!Pattern.compile("^[a-zA-Z0-9]+$").matcher(username).matches()){
            mainController.popUpWindow(Alert.AlertType.ERROR, "Specialtecken är ej tillåtna", "Användarnamnet får ej innehålla specialtecken, som t ex !, &, *");
            return false;
        }

        return true;
    }

    public Boolean isValidPassword(String password, String passwordRepeat){
        if (!password.equals(passwordRepeat)) {
            mainController.popUpWindow(Alert.AlertType.ERROR, "Felaktigt lösenord", "Lösenorden du angav stämmer ej överens med varandra");
            return false;
        } else if (password.length() < MIN_PASSWORD_LENGTH){
            mainController.popUpWindow(Alert.AlertType.ERROR, "Lösenordet är för kort", "Lösenordet måste vara minst 6 tecken långt");
            return false;
        }

        return true;
    }
}
