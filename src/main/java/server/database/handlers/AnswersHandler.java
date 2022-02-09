package server.database.handlers;

import spark.Spark;

import java.sql.Connection;

public class AnswersHandler {

    private Connection connection;

    public AnswersHandler(Connection connection) {
        this.connection = connection;
        initEntryPoints();
    }

    private void initEntryPoints() {
        Spark.get("/:id/answers", (req, res) -> {
            res.header("Content-Type", "application/json");
            return null;
        });
    }
}
