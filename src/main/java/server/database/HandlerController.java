package server.database;

import com.google.gson.JsonObject;
import server.database.handlers.AnswersHandler;
import server.database.handlers.QuestionsHandler;
import server.database.handlers.ResultsHandler;
import server.database.handlers.UsersHandler;

import java.sql.Connection;

public class HandlerController {

    private AnswersHandler answers;
    private QuestionsHandler questions;
    private ResultsHandler results;
    private UsersHandler users;

    public HandlerController(Connection connection) {
        initHandlers(connection);
    }

    private void initHandlers(Connection connection) {
        answers = new AnswersHandler(connection);
        questions = new QuestionsHandler(connection);
        results = new ResultsHandler(connection);
        users = new UsersHandler(connection);
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
}
