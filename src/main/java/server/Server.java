package server;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import model.NewQuestions;
import server.database.HandlerController;
import spark.Spark;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    private static Dotenv dotenv;
    private static HandlerController hc;
    private static Connection connection;

    public void testServer() {
        startServer();
    }

    public static void main(String[] args) {
        startServer();
    }

    public static void startServer() {
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
        hc.printAllUsers();
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

        //Change year of user
        Spark.put("/user/:id/:year", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.changeUserYear(req.params("id"), req.params("year"));
        });

                                            /* -----QUESTIONS ENTRYPOINTS----- */

        //Get random questions
        Spark.get("/questions/:year/:limit", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.getRandQuestions(req.params("year"), req.params("limit"));
        });

        //Get questions based on category
        Spark.get("/questions/:year/:category/:limit", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.getCategoryQuestions(req.params("year"), req.params("category"), req.params("limit"));
        });

                                            /* -----RESULTS ENTRYPOINTS----- */

        //POST users result
        Spark.post("/results/:user", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.addResult(req.params("user"), req.body());
        });

        Spark.get("/results/:user", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.getResult(req.params("user"));
        });

                                            /* -----DETAILED RESULTS ENTRYPOINTS----- */

        Spark.get("/results/detailed/:id", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.getDetailedResults(Integer.parseInt(req.params("id")));
        });

                                            /* -----CATEGORIES ENTRYPOINTS----- */

        Spark.get("/categories", (req, res) -> {
            res.header("Content-Type", "application/json");
            return hc.getAllCategories();
        });
    }
}
