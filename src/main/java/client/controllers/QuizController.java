package client.controllers;

import client.entity.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Answers;
import model.DetailedResults;
import model.NewQuestions;
import model.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/** Class QuizController that extends SceneControllerParent that handles the fxml file Quiz.fxml and creates actions in the Scene
 * @author Bajram Gerbeshi
 * @version 1.0
 */
public class QuizController extends SceneControllerParent implements InitializeSceneInterface {
    @FXML
    private Button currentQuestion;
    @FXML
    private RadioButton radioButtonOne;
    @FXML
    private RadioButton radioButtonTwo;
    @FXML
    private RadioButton radioButtonThree;
    @FXML
    private RadioButton radioButtonFour;
    @FXML
    private Label questionLabel;
    @FXML
    private ToggleGroup Group1;
    @FXML
    private Button previousQuestionButton;
    @FXML
    private Button nextQuestionButton;
    @FXML
    private Button submitResultsButton;

    private int questionNumber = -1;

    private int correctAnswer = -1;

    private Questions[] oldQuestions;

    private ArrayList<NewQuestions> questions;

    private ArrayList<Answers> answers;

    private QuizCompletedController quizCompleteController;

    private HashMap<NewQuestions, String> userAnswer = new HashMap<>();
    private DetailedResults[] detailedResults;

    /**
     * This method is used both when the user hits the button to move to previous question while in the quiz.
     * @param actionEvent The button action when user clicks previousQuestionButton.
     */
    public void previousQuestion(ActionEvent actionEvent) {
        updateLabels(false);
        if( questionNumber == 0){

            previousQuestionButton.setVisible(false);
        }

        if(questionNumber ==  questions.size() -2){
            nextQuestionButton.setVisible(true);
            submitResultsButton.setVisible(false);
        }
    }

    /**
     * This method is used when the label at the top right of the screen needs to be updated when the user moves on to the next question and/or previous question
     * @param nextQuestion Boolean that is true if the ueser is in the next question so that the label can be updated.
     */
    public void updateLabels(boolean nextQuestion) {
        if (nextQuestion) questionNumber++;
        else questionNumber--;

        currentQuestion.setText((questionNumber + 1) + "/" + questions.size());

        answers = questions.get(questionNumber).getAnswers();
        Collections.shuffle(answers);

        radioButtonOne.setText(answers.get(0).getAnswer());
        radioButtonTwo.setText(answers.get(1).getAnswer());
        radioButtonThree.setText(answers.get(2).getAnswer());
        radioButtonFour.setText(answers.get(3).getAnswer());

        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).isCorrect()) {
                correctAnswer = answers.indexOf(answers.get(i));
            }
        }

        questionLabel.setText("Fråga: " + questions.get(questionNumber).getQuestion());
    }

    /**
     * This method is used to check if the answer from the user is correct and gets sent to questions array so it can be used in QuizCompletedController to send the final score to the final scene.
     * @param questionNumber the number that is shown at the top right section of the screen (where the user is in the quiz).
     */
    public void checkAnswer(int questionNumber) {
        RadioButton selected = (RadioButton) Group1.getSelectedToggle();
        if (selected.getText().equals(answers.get(correctAnswer).getAnswer())) {
            System.out.println("Correct");
            userAnswer.put(questions.get(questionNumber),answers.get(correctAnswer).getAnswer());
        }
        else {
            System.out.println("False");
            userAnswer.put(questions.get(questionNumber),selected.getText());
        }

        /*
        RadioButton selectedButton = (RadioButton) Group1.getSelectedToggle();
        if (selectedButton.getText().equals(oldQuestions[questionNumber].getAnswer())){
            oldQuestions[questionNumber].correctAnswer(true, selectedButton.getText());
        } else {
            oldQuestions[questionNumber].correctAnswer(false, selectedButton.getText());
        }

         */
    }

    /**
     * This method is used when the user hits the button to move to next question while in the quiz.
     * @param actionEvent The button action
     */
    public void nextQuestion(ActionEvent actionEvent) {
        previousQuestionButton.setVisible(true);
        checkAnswer((questionNumber));
        if(questionNumber ==  questions.size() -2){
            nextQuestionButton.setVisible(false);
            submitResultsButton.setVisible(true);
        }
        updateLabels(true);
    }

    /**
     * This method is used when the results quiz is completed
     * @param actionEvent The button action
     */
    public void toResults(ActionEvent actionEvent) {
        checkAnswer(questionNumber);
        mainController.setUserAnswer(userAnswer);
        mainController.quizCompleted();
    }

    /**
     * This method is used when the user hits the button to exit the quiz prematurely
     * @param actionEvent The button action
     */
    public void quitQuiz(ActionEvent actionEvent) {
      boolean answer = mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Avsluta?" , "Är du säker på att du vill avsluta, dina svar sparas inte" );
      if (answer) {
          mainController.setScene(ScenesEnum.Home);
      }
    }

    public void setInitialValues(Object object) {
        questions = (ArrayList<NewQuestions>) object;
        detailedResults = new DetailedResults[questions.size()];
        submitResultsButton.setVisible(false);
        previousQuestionButton.setVisible(false);
        nextQuestionButton.setVisible(true);
        questionNumber = -1;
        updateLabels(true);
        questionLabel.setWrapText(true);
        previousQuestionButton.setVisible(false);
        radioButtonOne.setSelected(true);
    }
}