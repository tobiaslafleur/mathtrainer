package client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.NewUser;
import model.User;

public class SettingsController extends MainMenuControllerParent implements InitializeSceneInterface{
    @FXML
    public Label userInfoLabel;
    private NewUser user;

    @FXML
    ChoiceBox<String> year;

    @Override
    public void setInitialValues(Object object) {
        if(object != null) {
            user = (NewUser) object;
            userInfoLabel.setText("Användarnamn: " + user.getUsername() + "\n" + "Årskurs: " + user.getYear() + "\n");
        } else {
            userInfoLabel.setText("");
        }
    }

    public void changeButtonClicked() {
        String newYear = year.getValue();
        if (newYear != null) {
            user.setYear(Integer.parseInt(newYear));
            setInitialValues(user);
        }
    }


    //userInfoLabel.setText("Årskurs " + user.getYear() + "\n" + user.getSchool() + "\n");
    //ToDO: Metoder för att hantera de actions användaren kan göra i settings.
}
