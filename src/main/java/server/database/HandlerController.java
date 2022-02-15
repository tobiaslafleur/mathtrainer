package server.database;

import com.google.gson.JsonObject;
import model.Answers;
import model.Category;
import model.Questions;
import server.database.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class HandlerController {

    private AnswersHandler answers;
    private QuestionsHandler questions;
    private ResultsHandler results;
    private UsersHandler users;
    private CategoriesHandler categories;
    private DetailedResultsHandler detailedResults;

    public HandlerController(Connection connection) {
        initHandlers(connection);
    }

    private void initHandlers(Connection connection) {
        answers = new AnswersHandler(connection, this);
        questions = new QuestionsHandler(connection, this);
        results = new ResultsHandler(connection, this);
        users = new UsersHandler(connection, this);
        categories = new CategoriesHandler(connection, this);
        detailedResults = new DetailedResultsHandler(connection, this);
    }

    public Object getUser(String id) {
        return users.getUser(Integer.parseInt(id));
    }

    public Object addUser(String body) {
        return users.addUser(body);
    }

    public Object login(String body) {
        return users.login(body);
    }

    public Object getRandQuestions(String year, String limit) {
        return questions.getRandQuestions(Integer.parseInt(limit), Integer.parseInt(year));
    }

    public ArrayList<Answers> getAnswers(int id) {
        return answers.getAnswers(id);
    }

    public Object getCategoryQuestions(String year, String category, String limit) {
        return questions.getCategoryQuestions(
                Integer.parseInt(year),
                Integer.parseInt(category),
                Integer.parseInt(limit));
    }

    public Object addResult(String user, String body) {
        return results.addResult(
                Integer.parseInt(user),
                body
        );
    }

    public Object getResult(String user) {
        return results.getResults(Integer.parseInt(user));
    }

    public Object error(Exception e) {
        e.printStackTrace();
        HashMap<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return response;
    }

    public Object getCategory(int id) {
        return categories.getCategory(id);
    }

    public Object getQuestion(int id) {
        return questions.getQuestion(id);
    }

    public Object getAnswer(int id) {
        return answers.getAnswer(id);
    }

    public Object getDetailedResults(int id) {
        return detailedResults.getDetailedResults(id);
    }

    public Object getCategoryAsObj(int id) {
        return categories.getCategoryAsObj(id);
    }
}
