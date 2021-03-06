package client.controllers;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import model.*;

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

    private HashMap<Questions, String> userAnswer = new HashMap<>();
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

        for (Map.Entry<Questions, String> entry : userAnswer.entrySet()) {
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
        char grade;
        if (score == userAnswer.size()){ //A
            feedbackLabel.setText("Wow! Full pott!!");
            imageTrophy.setVisible(true);
            grade = 'A';
        } else if (score >= userAnswer.size()*0.75){ //B
            feedbackLabel.setText("Bra jobbat! Du kanske till och med kan f?? alla r??tt n??sta g??ng?");
            grade = 'B';
        } else if (score >= userAnswer.size()*0.50){ //E
            feedbackLabel.setText("Snyggt! Du ??r godk??nd! Med lite ??vning kanske du kan n?? ??nnu h??gre?");
            grade = 'E';
        } else if (score >= userAnswer.size()*0.30){ //F
            feedbackLabel.setText("N??ra godk??ntgr??nsen nu. Lite mer ??vning s?? sitter det nog. K??mpa p??!");
            grade = 'F';
        } else { //F
            feedbackLabel.setText("Ajd??, det gick ju s??d??r. Lite mer studier beh??vs nog innan n??sta f??rs??k.");
            grade = 'F';
        }

        User user = mainController.getCurrentUser();
        int categoryId = mainController.getCategoryId();

        HttpResponse<JsonNode> catResponse = Unirest.get("http://localhost:5000/categories/" + categoryId).asJson();
        Category category = new Gson().fromJson(String.valueOf(catResponse.getBody()), Category.class);

        Results results = new Results(user.getId(), category, grade, score, userAnswer.size());
        Unirest.post("http://localhost:5000/results/" + user.getId()).body(new Gson().toJson(results)).asJson();
    }

    public void setInitialValues(Object object) {
        userAnswer = (HashMap<Questions, String>) object;
        feedbackLabel.setWrapText(true);
        imageTrophy.setVisible(false);
        setResult();
    }

    public void showAnswers(ActionEvent actionEvent) {
        mainController.showDetailedResults();
    }
}