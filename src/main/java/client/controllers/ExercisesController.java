package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/** Controller ExercisesController handles the Exercises scene where the user can select which quiz they want to enter,
 *  the method reads the user input and starts the relevant quiz, this controller extends MainMenuControllerParent
 * @author Bajram Gerbeshi
 * @version 1.0
 */

public class ExercisesController extends MainMenuControllerParent {
    @FXML
    private Button arithmeticButton;
    @FXML
    private Button geometryButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button randomButton;


    /**
     * This method is used when the user hits the button to select which quiz they want to enter.
     *
     * @param actionEvent The button action
     */
    public void buttonClicked(ActionEvent actionEvent) {
        String category = "";

        if (actionEvent.getSource() == arithmeticButton) { //3
            category = "Arithmetic";
        }
        else if (actionEvent.getSource() == geometryButton) { //2
            category = "Geometry";
        }
        else if (actionEvent.getSource() == statisticsButton) { //1
            category = "Statistics";
        }
        else if (actionEvent.getSource() == randomButton) { //4
            category = "Random";
        }
        mainController.startQuiz(category);
    }
}
