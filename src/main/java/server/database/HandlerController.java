package server.database;

import com.google.gson.JsonObject;
import model.Answers;
import server.database.handlers.AnswersHandler;
import server.database.handlers.QuestionsHandler;
import server.database.handlers.ResultsHandler;
import server.database.handlers.UsersHandler;

import java.sql.Connection;
import java.util.ArrayList;

public class HandlerController {

    private AnswersHandler answers;
    private QuestionsHandler questions;
    private ResultsHandler results;
    private UsersHandler users;

    public HandlerController(Connection connection) {
        initHandlers(connection);
    }

    private void initHandlers(Connection connection) {
        answers = new AnswersHandler(connection, this);
        questions = new QuestionsHandler(connection, this);
        results = new ResultsHandler(connection, this);
        users = new UsersHandler(connection, this);
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
}
