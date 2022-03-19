package server.database;

import model.Answers;
import server.database.handlers.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class HandlerController {

    private AnswersHandler answers;
    private QuestionsHandler questions;
    private ResultsHandler results;
    private UsersHandler users;
    private CategoriesHandler categories;

    public HandlerController(Connection connection) {
        initHandlers(connection);
    }

    private void initHandlers(Connection connection) {
        answers = new AnswersHandler(connection, this);
        questions = new QuestionsHandler(connection, this);
        results = new ResultsHandler(connection, this);
        users = new UsersHandler(connection, this);
        categories = new CategoriesHandler(connection, this);
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

    public Object changeUserYear(String id, String year) {
        return users.changeUserYear(Integer.parseInt(id), Integer.parseInt(year));
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

    public Object getCategoryJson(int id) {
        return categories.getCategoryJson(id);
    }

    public Object getCategoryObj(int id) {
        return categories.getCategoryObj(id);
    }

    public Object getAllCategories() {
        return categories.getAllCategories();
    }
}
