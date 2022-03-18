package client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
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
            if (user != null) {
                HttpResponse<JsonNode> updateResponse = Unirest.put("http://localhost:5000/user/" + user.getId() + "/" + newYear).asJson();
                if (updateResponse.getStatus() == 200) {
                    user.setYear(Integer.parseInt(newYear));
                    setInitialValues(user);
                }
            }
        }
    }


    //userInfoLabel.setText("Årskurs " + user.getYear() + "\n" + user.getSchool() + "\n");
    //ToDO: Metoder för att hantera de actions användaren kan göra i settings.
}
