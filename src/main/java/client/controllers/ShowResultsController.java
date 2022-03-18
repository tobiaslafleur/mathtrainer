package client.controllers;

import client.entity.ScenesEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DetailedResults;
import model.NewDetailedResults;
import model.NewQuestions;
import model.Questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ShowResultsController extends SceneControllerParent implements InitializeSceneInterface{
    @FXML
    private TableView<NewDetailedResults> tableView = new TableView<>();

    @FXML
    private TableColumn<NewDetailedResults, String> questionColumn;

    @FXML
    private TableColumn<NewDetailedResults, String> answerColumn;

    @FXML
    private TableColumn<NewDetailedResults, String> yourAnswerColumn;

   // private Questions[] questions;

    private NewDetailedResults[] detailedResults;



    @Override
    public void setInitialValues(Object object) {
        detailedResults = (NewDetailedResults[]) object;
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
        yourAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("guessedAnswer"));

        tableView.setItems(getQuestions());
    }

    public void goToHome(ActionEvent actionEvent) {
        int score = 0;
//        for (Questions question : questions){
//            if (question.getAnswer().equals(question.getUserAnswer())){
//                score++;
//            }
//        }
        //mainController.reportResult(score);
        mainController.showHomeScreen();
    }

    public void backToScore(ActionEvent actionEvent) {
        mainController.setScene(ScenesEnum.QuizCompleted);
    }

    /**
     * Get all of the questions
     */
    public ObservableList<NewDetailedResults> getQuestions()
    {
        ObservableList<NewDetailedResults> values = FXCollections.observableArrayList();
        for (NewDetailedResults ndr : detailedResults) {
            values.add(new NewDetailedResults(ndr.getQuestion(), ndr.getCorrectAnswer(), ndr.getGuessedAnswer()));
        }
        return values;
    }

//    public void setQuestions(ArrayList<NewQuestions> questions) {
//        this.questions = questions;
//    }

}
