package server.database.handlers;

import com.google.gson.Gson;
import model.NewUser;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UsersHandler {

    private Connection connection;
    private Gson gson;

    public UsersHandler(Connection connection) {
        this.connection = connection;
        gson = new Gson();
    }

    public Object getUser(int id) {
        try {
            String query = """
                    SELECT * FROM users
                    WHERE id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            NewUser user = null;

            while(rs.next()) {
                user = new NewUser(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
            }

            if(user != null) {
                return gson.toJson(user);
            } else {
                HashMap<String, String> error = new HashMap<>();
                error.put("error", "User ID not found");
                return gson.toJson(error);
            }

        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return gson.toJson(error);
        }
    }

    public Object addUser(String body) {
        NewUser user = gson.fromJson(body, NewUser.class);

        try {
            String query = """
                    INSERT INTO users (id, username, password, year)
                    VALUES (DEFAULT, ?, ?, ?)
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getYear());
            preparedStatement.execute();

            return 200;

        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return gson.toJson(error);
        }
    }

    public Object login(String body) {
        NewUser user = gson.fromJson(body, NewUser.class);

        try {
            String query = """
                    SELECT * FROM users
                    WHERE username = ?
                    AND password = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();

            boolean loggedIn = false;
            while(rs.next()) {
                loggedIn = true;
            }

            HashMap<String, String> response = new HashMap<>();
            if(loggedIn) response.put("response", "Login Successful");
            else response.put("response", "Credentials doesn't match");

            return gson.toJson(response);

        } catch (SQLException e) {
            e.printStackTrace();
            HashMap<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());

            return response;
        }
    }
}
