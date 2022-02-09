package server.database.handlers;

import server.database.HandlerController;

import java.sql.Connection;

public class ResultsHandler {

    private Connection connection;

    public ResultsHandler(Connection connection, HandlerController handlerController) {
        this.connection = connection;
    }
}
