package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.cdimascio.dotenv.Dotenv;
import model.NewUser;
import spark.Spark;

import java.sql.*;

public class Server {
    private static Dotenv dotenv;
    private static DBUsers users;
    private static Connection connection;

    public static void main(String[] args) {
        dotenv = Dotenv.load();

        try {
            connection = DriverManager.getConnection(dotenv.get("DB_CONNECTION_URI"));
            initSpark();
            initControllers();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void initControllers() {
        users = new DBUsers(connection);
    }

    public static void initSpark() {
        Spark.port(Integer.parseInt(dotenv.get("PORT")));

        Spark.get("/", (req, res) -> "Hello");

        Spark.post("/user", (req, res) -> {
            res.header("Content-Type", "application/html");
            Gson gson = new Gson();
            NewUser user = gson.fromJson(req.body(), NewUser.class);

            return users.addUser(user);
        });

        Spark.post("/login", (req, res) -> {
            res.header("Content-Type", "application/json");
            Gson gson = new Gson();
            NewUser user = gson.fromJson(req.body(), NewUser.class);

            return users.login(user);
        });
    }
}
