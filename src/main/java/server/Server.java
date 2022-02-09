package server;

import io.github.cdimascio.dotenv.Dotenv;
import server.database.HandlerController;
import spark.Spark;

import java.sql.*;

public class Server {
    private static Dotenv dotenv;
    private static HandlerController hc;
    private static Connection connection;

    public static void main(String[] args) {
        dotenv = Dotenv.load();

        try {
            connection = DriverManager.getConnection(dotenv.get("DB_CONNECTION_URI"));
            initSpark();
            initDatabase();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void initDatabase() {
        hc = new HandlerController(connection);
    }

    public static void initSpark() {
        Spark.port(Integer.parseInt(dotenv.get("PORT")));

                                        /* -----USER ENTRYPOINTS----- */

        //Get info of specific user
        Spark.get("/user/:id", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.getUser(req.params("id"));
        });

        //Create account
        Spark.post("/user", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.addUser(req.body());
        });

        //Login
        Spark.post("/login", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.login(req.body());
        });
    }
}
