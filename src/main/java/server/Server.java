package server;

import io.github.cdimascio.dotenv.Dotenv;
import spark.Spark;

import java.sql.*;

public class Server {
    private static Dotenv dotenv;
    private static Connection connection;

    public static void main(String[] args) {
        dotenv = Dotenv.load();

        try {
            connection = DriverManager.getConnection(dotenv.get("DB_CONNECTION_URI"));
            initSpark();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void initSpark() {
        Spark.port(Integer.parseInt(dotenv.get("PORT")));

        Spark.get("/", (req, res) -> "Hello");
    }
}
