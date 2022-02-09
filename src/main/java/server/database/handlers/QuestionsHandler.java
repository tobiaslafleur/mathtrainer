package server.database.handlers;

import java.sql.Connection;

public class QuestionsHandler {

    private Connection connection;

    public QuestionsHandler(Connection connection) {
        this.connection = connection;
    }
}
