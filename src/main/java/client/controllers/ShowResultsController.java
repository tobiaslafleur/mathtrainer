package client.controllers;

import client.entity.ScenesEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NewQuestions;
import model.Questions;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowResultsController extends SceneControllerParent implements InitializeSceneInterface{
    @FXML
    private TableView<HashMap> tableView = new TableView<>();

    @FXML
    private TableColumn<NewQuestions, String> questionColumn;

    @FXML
    private TableColumn<NewQuestions, String> answerColumn;

    @FXML
    private TableColumn<NewQuestions, String> yourAnswerColumn;

   // private Questions[] questions;

    private HashMap<NewQuestions, String> userAnswer;



    @Override
    public void setInitialValues(Object object) {
        userAnswer = (HashMap<NewQuestions, String>) object;
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        yourAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("userAnswer"));

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
    }

    public void backToScore(ActionEvent actionEvent) {
        mainController.setScene(ScenesEnum.QuizCompleted);
    }
    /**
     * Get all of the questions
     */
    public ObservableList<HashMap> getQuestions()
    {
        ObservableList<HashMap> values = FXCollections.observableArrayList();
            values.add(userAnswer);

        return values;
    }

//    public void setQuestions(ArrayList<NewQuestions> questions) {
//        this.questions = questions;
//    }

}
