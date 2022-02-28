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

    /**
     * Returns to the login scene if the user backs out of creating a new user.
     */
    public void backClicked(){
        mainController.setScene(ScenesEnum.LogIn);
    }

    public void createUserClicked() {
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username.getText());
        if (username.getText().length() < 5 || username.getText().length() > 20) {
            mainController.popUpWindow(Alert.AlertType.ERROR, "Användarnamnet är för kort eller långt", "Användarnamnet måste vara mellan 5 och 20 tecken långt");
        } else if(!matcher.matches()){
            mainController.popUpWindow(Alert.AlertType.ERROR, "Specialtecken är ej tillåtna", "Användarnamnet får ej innehålla specialtecken, som t ex !, &, *");
        } else if (!password.getText().equals(passwordRepeat.getText())) {
            mainController.popUpWindow(Alert.AlertType.ERROR, "Felaktigt lösenord", "Lösenorden du angav stämmer ej överens med varandra");
        } else if (password.getText().length() < 6){
            mainController.popUpWindow(Alert.AlertType.ERROR, "Lösenordet är för kort", "Lösenordet måste vara minst 6 tecken långt");
        } else {
            String userYear = year.getValue();
            NewUser user = new NewUser(1, username.getText(), password.getText(), Integer.parseInt(userYear));

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

    @Override
    public void setInitialValues(Object object) {
        username.setText("");
        password.setText("");
        passwordRepeat.setText("");
        year.getSelectionModel().select(0);
    }
}
