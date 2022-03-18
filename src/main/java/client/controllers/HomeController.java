package client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import model.User;
import model.Results;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Controller for handling button-presses in the scene Home.fxml. It contains only a method to initialize correct values.
 * The controller, as all other controllers that are part of the Main menu, extends the MainMenuControllerParent.
 * @author Niklas Hultin
 * @version 1.0
 */
public class HomeController extends MainMenuControllerParent implements InitializeSceneInterface {

    @FXML
    Label welcomeLabel;
    @FXML
    ProgressBar pbAllCategories;
    @FXML
    ProgressBar pbGeometry;
    @FXML
    ProgressBar pbStatistics;
    @FXML
    ProgressBar pbCounting;
    @FXML
    ProgressBar pbRandom;
    @FXML
    ImageView countingTrophy;
    @FXML
    ImageView statisticsTrophy;
    @FXML
    ImageView randomTrophy;
    @FXML
    ImageView geometryTrophy;
    @FXML
    ImageView totalTrophy;
    @FXML
    Label userInfoLabel;
    @FXML
    Label statisticsPercent;
    @FXML
    Label countingPercent;
    @FXML
    Label geometryPercent;
    @FXML
    Label randomPercent;
    @FXML
    Label totalPercent;

    private User user;
    private int arithmeticScore;
    private int statisticsScore;
    private int geometryScore;
    private int randomScore;

    @Override
    public void setInitialValues(Object object) {
        if(object != null) {
            user = (User) object;
            welcomeLabel.setText("Välkommen " + user.getUsername());
            userInfoLabel.setText("Årskurs " + user.getYear() + "\n");
            if (!user.isGuest()) {
                setProgress();
            }
            else resetProgress();
        }
    }

    private void resetProgress() {
        pbCounting.setProgress((double) 0/10);
        pbStatistics.setProgress((double) 0/10);
        pbRandom.setProgress((double) 0/10);
        pbGeometry.setProgress((double) 0/10);
        pbAllCategories.setProgress((double) 0/40);

        countingTrophy.setVisible(false);
        statisticsTrophy.setVisible(false);
        randomTrophy.setVisible(false);
        geometryTrophy.setVisible(false);
        totalTrophy.setVisible(false);

        countingPercent.setText(0 + "%");
        statisticsPercent.setText(0 + "%");
        randomPercent.setText(0 + "%");
        geometryPercent.setText(0 + "%");
        totalPercent.setText(0 + "%");
    }

    public void setProgress() {
        String url = "http://localhost:5000/results/";
        ArrayList<Results> results = Unirest.get(url + user.getId()).asObject(new GenericType<ArrayList<Results>>() {}).getBody();

        if (results != null) {
            ArrayList<Integer> arithmeticScores = new ArrayList<>();
            ArrayList<Integer> statisticsScores = new ArrayList<>();
            ArrayList<Integer> geometryScores = new ArrayList<>();
            ArrayList<Integer> randomScores = new ArrayList<>();

            for (Results r : results) {
                switch (r.getCategoryId().getCategory()) {
                    case "Arithmetic" -> arithmeticScores.add(r.getScore());
                    case "Statistics" -> statisticsScores.add(r.getScore());
                    case "Geometry" -> geometryScores.add(r.getScore());
                    case "Random" -> randomScores.add(r.getScore());
                }
            }

            checkArithmetic(arithmeticScores);
            checkStatistics(statisticsScores);
            checkGeometry(geometryScores);
            checkRandom(randomScores);
            checkAll();
        }
    }

    private void checkArithmetic(ArrayList<Integer> scores) {
        if (scores.size() > 0) {
            arithmeticScore = Collections.max(scores);
            pbCounting.setProgress((double) arithmeticScore / 10);
            countingPercent.setText(arithmeticScore * 10 + "%");
            if (arithmeticScore == 10) {
                countingTrophy.setVisible(true);
            }
        }
    }

    private void checkStatistics(ArrayList<Integer> scores) {
        if (scores.size() > 0) {
            statisticsScore = Collections.max(scores);
            pbStatistics.setProgress((double) statisticsScore / 10);
            statisticsPercent.setText(statisticsScore * 10 + "%");
            if (statisticsScore == 10) {
                statisticsTrophy.setVisible(true);
            }
        }
    }

    private void checkGeometry(ArrayList<Integer> scores) {
        if (scores.size() > 0) {
            geometryScore = Collections.max(scores);
            pbGeometry.setProgress((double) geometryScore / 10);
            geometryPercent.setText(geometryScore * 10 + "%");
            if (geometryScore == 10) {
                geometryTrophy.setVisible(true);
            }
        }
    }

    private void checkRandom(ArrayList<Integer> scores) {
        if (scores.size() > 0) {
            randomScore = Collections.max(scores);
            pbRandom.setProgress((double) randomScore / 10);
            randomPercent.setText(randomScore * 10 + "%");
            if (randomScore == 10) {
                randomTrophy.setVisible(true);
            }
        }
    }

    private void checkAll() {
        int all = arithmeticScore + statisticsScore + geometryScore + randomScore;
        pbAllCategories.setProgress((double) all / 40);
        totalPercent.setText(all * 2.5 + "%");
        if (all == 40) {
            totalTrophy.setVisible(true);
        }
    }
}
