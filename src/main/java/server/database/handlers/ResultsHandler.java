package server.database.handlers;

import java.sql.Connection;

public class ResultsHandler {

    private Connection connection;

    public ResultsHandler(Connection connection) {
        this.connection = connection;
    }
}
