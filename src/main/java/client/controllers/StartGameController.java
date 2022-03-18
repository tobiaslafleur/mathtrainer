package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.User;

/**
 * This class is the initial view before starting up the game. It displays information about the game including the
 * users highest score in the game if the user is logged in. Clicking the button leads to the actual game scene.
 */
public class StartGameController extends MainMenuControllerParent implements InitializeSceneInterface {
    @FXML
    Label bestScoreLabel;
    @FXML
    Label gameInformationLabel;


    @Override
    public void setInitialValues(Object object) {
        gameInformationLabel.setWrapText(true);
        User user = (User) object;
        if (user != null) {
            if (user.getGameScore() == 0) {
                bestScoreLabel.setText("Inget resultat");
                bestScoreLabel.setTextFill(Color.web("#ff0000", 0.8));
            }
            else {
                bestScoreLabel.setText(user.getGameScore() + "/16");
            }
        }
    }

    public void startGameClicked(ActionEvent actionEvent) {
        mainController.startGame();
    }
}
