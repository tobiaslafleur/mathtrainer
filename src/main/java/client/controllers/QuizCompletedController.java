package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Answers;
import model.NewQuestions;
import model.Questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Class QuizCompletedController that extends SceneControllerParent that handles the fxml file QuizCompleted.fxml and shows the user their final score from the quiz
 * @author Bajram Gerbeshi, Niklas Hultin
 * @version 1.0
 */
public class QuizCompletedController extends SceneControllerParent implements InitializeSceneInterface {

    @FXML
    private Label scoreLabel;
    @FXML
    private Label feedbackLabel;
    @FXML
    private ImageView imageTrophy;

  //private Questions[] questions;
  //private ArrayList<NewQuestions> questions;
    private HashMap<NewQuestions, String> userAnswer = new HashMap<>();
    private int score;

    /**
     * This method is used when the user wants to continue to the home screen from the quizCompleted scene.
     * @param actionEvent The button action
     */
    public void continueMenu(ActionEvent actionEvent){
        //mainController.reportResult(score);
    }

    /**
     * This method is used to set the results in the final scene and to get the results from the questions array.
     */

    public void setResult(){
        score = 0;

        for(Map.Entry<NewQuestions, String> entry : userAnswer.entrySet()){
            for(Answers answers :entry.getKey().getAnswers()){
                if(answers.isCorrect()){
                if(answers.getAnswer().equals(entry.getValue())){
                        score++;
                    }
                }
            }
        }

        scoreLabel.setText(score + "/" + "10");
        showFeedback(score);
    }

    public void showFeedback(int score){
        if (score == userAnswer.size()){
            feedbackLabel.setText("Wow! Full pott!!");
            imageTrophy.setVisible(true);
        } else if (score >= userAnswer.size()*0.75){
            feedbackLabel.setText("Bra jobbat! Du kanske till och med kan få alla rätt nästa gång?");
        } else if (score >= userAnswer.size()*0.50){
            feedbackLabel.setText("Snyggt! Du är godkänd! Med lite övning kanske du kan nå ännu högre?");
        } else if (score >= userAnswer.size()*0.30){
            feedbackLabel.setText("Nära godkäntgränsen nu. Lite mer övning så sitter det nog. Kämpa på!");
        } else {
            feedbackLabel.setText("Ajdå, det gick ju sådär. Lite mer studier behövs nog innan nästa försök.");
        }
    }

    public void setInitialValues(Object object) {
        userAnswer = (HashMap<NewQuestions, String>) object;
        feedbackLabel.setWrapText(true);
        imageTrophy.setVisible(false);
        setResult();
    }

    public void showAnswers(ActionEvent actionEvent) {
        mainController.showDetailedResults();
    }
}