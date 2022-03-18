package client.controllers;

import client.entity.ScenesEnum;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Class QuizCompletedController that extends SceneControllerParent that handles the fxml file QuizCompleted.fxml and shows the user their final score from the quiz
 * @author Bajram Gerbeshi, Niklas Hultin, Duy Nguyen, Hanis Saley
 * @version 1.1
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
    private NewUser user;
    private int categoryId;
    private int score;

    /**
     * This method is used when the user wants to continue to the home screen from the quizCompleted scene.
     * @param actionEvent The button action
     */
    public void continueMenu(ActionEvent actionEvent){
        mainController.showHomeScreen();
        score = 0;
        userAnswer.clear();
    }

    /**
     * This method is used to set the results in the final scene and to get the results from the questions array.
     */

    public void setResult(){
        score = 0;

        for (Map.Entry<NewQuestions, String> entry : userAnswer.entrySet()) {
            for (Answers answers :entry.getKey().getAnswers()) {
                if (answers.isCorrect()) {
                    if(answers.getAnswer().equals(entry.getValue())) {
                        score++;
                    }
                }
            }
        }

        scoreLabel.setText(score + "/" + userAnswer.size());
        showFeedback(score);
    }

    public void showFeedback(int score) {
        char grade = ' ';
        if (score == userAnswer.size()){ //A
            feedbackLabel.setText("Wow! Full pott!!");
            imageTrophy.setVisible(true);
            grade = 'A';
        } else if (score >= userAnswer.size()*0.75){ //B
            feedbackLabel.setText("Bra jobbat! Du kanske till och med kan få alla rätt nästa gång?");
            grade = 'B';
        } else if (score >= userAnswer.size()*0.50){ //E
            feedbackLabel.setText("Snyggt! Du är godkänd! Med lite övning kanske du kan nå ännu högre?");
            grade = 'E';
        } else if (score >= userAnswer.size()*0.30){ //F
            feedbackLabel.setText("Nära godkäntgränsen nu. Lite mer övning så sitter det nog. Kämpa på!");
            grade = 'F';
        } else { //F
            feedbackLabel.setText("Ajdå, det gick ju sådär. Lite mer studier behövs nog innan nästa försök.");
            grade = 'F';
        }

        user = mainController.getCurrentUser();
        categoryId = mainController.getCategoryId();

        HttpResponse<JsonNode> catResponse = Unirest.get("http://localhost:5000/categories/" + categoryId).asJson();
        Category category = new Gson().fromJson(String.valueOf(catResponse.getBody()), Category.class);

        Results results = new Results(user.getId(), category, grade, score, userAnswer.size());
        Unirest.post("http://localhost:5000/results/" + user.getId()).body(new Gson().toJson(results)).asJson();
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