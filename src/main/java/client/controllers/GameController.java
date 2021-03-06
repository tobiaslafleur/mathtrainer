package client.controllers;

import client.entity.ScenesEnum;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Answers;

import model.Questions;

import java.util.ArrayList;

//Denna är kopplad till GameScene

/**
 * Class GameController that extends SceneControllerParent that handles the game(gamesceens) which is a mathgame that runs on a time schedule
 *
 * @author Johanna Dahlborn , Hanis Saley, Alfred Andersson
 * @version 1.1
 */
public class GameController extends SceneControllerParent implements InitializeSceneInterface {
    public Label countdownLabel = new Label();
    public Label plusLeftLabel;
    public Label minusLeftLabel;
    public Label devidedLeftLabel;
    public Button startQuiz = new Button();
    public Button nextQuestion = new Button();
    @FXML
    private Button currentSlide = new Button();
    public Label additionLeftLabel;
    public Button answerBtn;
    public TextField sumPlus;
    public TextField sumMinus;
    public TextField sumMulti;
    public TextField sumDiv;
    int correctAnswer;


//TODO Fix Score
    private ArrayList<Questions> questions;
    private ArrayList<Answers> answers;
    private ArrayList<Answers> answers1;
    private ArrayList<Answers> answers2;
    private ArrayList<Answers> answers3;
    private ArrayList<Integer> allAnswers = new ArrayList<>();

    private int questionNumber = -1;
    private int userAnswer = 0;
    private int userAnswer1 = 0;
    private int userAnswer2 = 0;
    private int userAnswer3 = 0;
    private int currentNumberOfSlide = 0;
    @FXML

    private Timeline timeline = new Timeline();

    private final int START_TIME = 60;
    private int timeSeconds = START_TIME;

    @Override
    public void setInitialValues(Object object) {
        questions = (ArrayList<Questions>) object;
        countdownLabel.setText(Integer.toString(START_TIME));
        questionNumber = -1;
        minusLeftLabel.setWrapText(true);
        additionLeftLabel.setWrapText(true);
        plusLeftLabel.setWrapText(true);
        devidedLeftLabel.setWrapText(true);
        answerBtn.setDisable(true);
        nextQuestion.setDisable(true);
        SetEditableTextBox(false);
    }

    public void resetRounds(){
        correctAnswer = 0;
        currentNumberOfSlide = 0;
        currentSlide.setText("1/4");
    }

    public void reset(){
        resetTimer();
        resetQuestions();
        resetTextBox();
        SetEditableTextBox(false);
    }

    public void resetTimer(){
        timeline.stop();
        timeSeconds = START_TIME;
        countdownLabel.setText(Integer.toString(timeSeconds));
    }

    public void resetQuestions(){
        plusLeftLabel.setText("?");
        minusLeftLabel.setText("?");
        additionLeftLabel.setText("?");
        devidedLeftLabel.setText("?");
    }

     /**
     * Clears all the textboxes.
     */
    public void resetTextBox(){
        sumPlus.clear();
        sumMinus.clear();
        sumMulti.clear();
        sumDiv.clear();
        sumPlus.setStyle("-fx-background-color: WHITE;");
        sumMinus.setStyle("-fx-background-color: WHITE;");
        sumMulti.setStyle("-fx-background-color: WHITE;");
        sumDiv.setStyle("-fx-background-color: WHITE;");
    }

    public void stripTextBoxCharacters(){
        sumPlus.setText(sumPlus.getText().replaceAll("[^\\d]", ""));;
        sumMinus.setText(sumMinus.getText().replaceAll("[^\\d]", ""));;
        sumMulti.setText(sumMulti.getText().replaceAll("[^\\d]", ""));;
        sumDiv.setText(sumDiv.getText().replaceAll("[^\\d]", ""));;
    }

    /*
     * Enable or disable text boxes from being editable or not.
     */
    public void SetEditableTextBox(Boolean editable){
        sumPlus.setEditable(editable);
        sumMinus.setEditable(editable);
        sumMulti.setEditable(editable);
        sumDiv.setEditable(editable);
    }

    /**
     * This method is if you want to quit the game. Ends the quiz and ends the clock.
     */
    public void quitGame(ActionEvent actionEvent) {
        if (mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Avsluta?", "Är du säker på att du vill avsluta, dina svar sparas inte")) {
            mainController.setScene(ScenesEnum.Exercises);
            startQuiz.setDisable(false);
            nextQuestion.setDisable(true);
            answerBtn.setDisable(true);
            timeline.stop();
            resetRounds();
            reset();
        }
    }

    /**
     * This method starts the game, and adds all the random values and starts the timer.
     */
    public void startQuiz() {
        updateLabels();
        startQuiz.setDisable(true);
        nextQuestion.setDisable(true);
        answerBtn.setDisable(false);
        timer();
        SetEditableTextBox(true);
    }

    /*
     * Move to next hidden questions. Need to press start to start the game again.
     */
    public void updateNextQuestion(ActionEvent actionEvent){
        nextQuestion.setDisable(true);
        startQuiz.setDisable(false);
        answerBtn.setDisable(true);
        reset();
    }

    public void updateLabels() {
        plusLeftLabel.setText(questions.get(questionNumber+1).getQuestion());
        minusLeftLabel.setText(questions.get(questionNumber+2).getQuestion());
        additionLeftLabel.setText(questions.get(questionNumber+3).getQuestion());
        devidedLeftLabel.setText(questions.get(questionNumber+4).getQuestion());

        answers = questions.get(questionNumber+1).getAnswers();
        answers1 = questions.get(questionNumber+2).getAnswers();
        answers2 = questions.get(questionNumber+3).getAnswers();
        answers3 = questions.get(questionNumber+4).getAnswers();

        allAnswers.add(Integer.parseInt(answers.get(0).getAnswer()));
        allAnswers.add(Integer.parseInt(answers1.get(0).getAnswer()));
        allAnswers.add(Integer.parseInt(answers2.get(0).getAnswer()));
        allAnswers.add(Integer.parseInt(answers3.get(0).getAnswer()));

        questionNumber+=3;

        if(currentNumberOfSlide <= 4){
            currentNumberOfSlide++;
        }
        currentSlide.setText(currentNumberOfSlide + "/4");
        resetTextBox();
    }

        /**
         * Method that adds a timer to the game
         */
    public void timer() {
        countdownLabel.setText(Integer.toString(timeSeconds));
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds = START_TIME;
        countdownLabel.setTextFill(Color.RED);
        countdownLabel.setText(Integer.toString(timeSeconds));
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler() {

                            @Override
                            public void handle(Event event) {
                                timeSeconds--;
                                countdownLabel.setText(Integer.toString(timeSeconds));
                                if (timeSeconds <= 0) {
                                    timeline.stop();
                                    SetEditableTextBox(false);
                                    stripTextBoxCharacters();
                                    CheckAnswer();
                                }
                            }
                        }));
        timeline.playFromStart();
    }

    /**
     *check if answer is correct
     */
    public void CheckAnswer() {
        try{
            userAnswer = Integer.parseInt(sumPlus.getText().isBlank() ? "0" : sumPlus.getText().trim());
            userAnswer1 = Integer.parseInt(sumMinus.getText().isBlank() ? "0" : sumMinus.getText().trim());
            userAnswer2 = Integer.parseInt(sumMulti.getText().isBlank() ? "0" : sumMulti.getText().trim());
            userAnswer3 = Integer.parseInt(sumDiv.getText().isBlank() ? "0" : sumDiv.getText().trim());

            timeline.stop();
                SetEditableTextBox(false);

                if (allAnswers.get(0).equals(userAnswer)) {
                    correctAnswer += 1;
                    sumPlus.setStyle("-fx-background-color: LIGHTGREEN;");
                } else {
                    sumPlus.setStyle("-fx-background-color: PALEVIOLETRED;");
                }
                if (allAnswers.get(1).equals(userAnswer1)) {
                    correctAnswer += 1;
                    sumMinus.setStyle("-fx-background-color: LIGHTGREEN;");
                } else {
                    sumMinus.setStyle("-fx-background-color: PALEVIOLETRED;");
                }
                if (allAnswers.get(2).equals(userAnswer2)) {
                    correctAnswer += 1;
                    sumMulti.setStyle("-fx-background-color: LIGHTGREEN;");
                } else {
                    sumMulti.setStyle("-fx-background-color: PALEVIOLETRED;");
                }
                if (allAnswers.get(3).equals(userAnswer3)) {
                    correctAnswer += 1;
                    sumDiv.setStyle("-fx-background-color: LIGHTGREEN;");
                } else {
                    sumDiv.setStyle("-fx-background-color: PALEVIOLETRED;");
                }
                
                if(questionNumber == 11){
                    nextQuestion.setDisable(true);
                }
                else{
                    nextQuestion.setDisable(false);
                }
                
                answerBtn.setDisable(true);
                allAnswers.clear();
                checkIfGameFinished();
        }catch (Exception e){
            mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "OBS!" , "Går ej att rätta icke-heltal" );
            nextQuestion.setDisable(true);
        }
        if(!startQuiz.isDisabled()){
            mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Starta spelet!" , "Starta först spelet" );
        }else{
            startQuiz.setDisable(true);
        }
    }

    public void checkIfGameFinished(){
        if(currentNumberOfSlide >= 4){
            mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Poäng" , "Poäng: "+ correctAnswer +"/16" );
            mainController.getCurrentUser().setGameScore(correctAnswer);
            mainController.startGameSceneSetup();
            currentNumberOfSlide = 0;
            correctAnswer = 0;
        }
    }
}





