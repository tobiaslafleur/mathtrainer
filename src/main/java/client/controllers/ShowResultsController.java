package client.controllers;

import client.entity.ScenesEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DetailedResults;

import java.net.URL;

public class ShowResultsController extends SceneControllerParent implements InitializeSceneInterface{
    @FXML
    private TableView<DetailedResults> tableView = new TableView<>();

    @FXML
    private TableColumn<DetailedResults, String> questionColumn;

    @FXML
    private TableColumn<DetailedResults, String> answerColumn;

    @FXML
    private TableColumn<DetailedResults, String> yourAnswerColumn;

    @FXML
    private URL location;

    private DetailedResults[] detailedResults;

    @Override
    public void setInitialValues(Object object) {
        detailedResults = (DetailedResults[]) object;
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
        yourAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("guessedAnswer"));

        tableView.setItems(getQuestions());
    }

    public void goToHome(ActionEvent actionEvent) {
        mainController.showHomeScreen();
    }

    public void backToScore(ActionEvent actionEvent) {
        mainController.setScene(ScenesEnum.QuizCompleted);
    }

    /**
     * Get all of the questions
     */
    public ObservableList<DetailedResults> getQuestions()
    {
        ObservableList<DetailedResults> values = FXCollections.observableArrayList();
        for (DetailedResults ndr : detailedResults) {
            values.add(new DetailedResults(ndr.getQuestion(), ndr.getCorrectAnswer(), ndr.getGuessedAnswer()));
        }
        return values;
    }

    public URL getLocation() {
        return location;
    }
}
