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
     * @param actionEvent
     */
    public void backClicked(ActionEvent actionEvent){
        mainController.setScene(ScenesEnum.LogIn);
    }

    public void createUserClicked(ActionEvent actionEvent) {
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
                System.out.println("User added"); //Todo visa klienten
            }
            else {
                System.out.println("Error"); //Todo visa klienten
            }
        }
    }


    /*
    /**
     * Checks that the values for username and password are reasonable and if so, sends them to the MainController.
     * @param actionEvent
     */
    /*
    public void createUserClicked(ActionEvent actionEvent) {
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
            mainController.newUser(username.getText(), password.getText(), year.getValue());
        }
    }
     */


    @Override
    public void setInitialValues(Object object) {
        username.setText("");
        password.setText("");
        passwordRepeat.setText("");
        year.getSelectionModel().select(0);
    }
}
