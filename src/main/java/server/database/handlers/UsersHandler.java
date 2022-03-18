package server.database.handlers;

import com.google.gson.Gson;
import model.NewUser;
import server.database.HandlerController;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.HashMap;

public class UsersHandler {

    private Connection connection;
    private Gson gson;
    private HandlerController hc;

    public UsersHandler(Connection connection, HandlerController hc) {
        this.connection = connection;
        this.hc = hc;
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

        } catch (SQLException e) {
            return hc.error(e);
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
            preparedStatement.executeUpdate();

            return 200;

        } catch (SQLException e) {

            return hc.error(e);
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
            String id = "";

            while (rs.next()) {
                if (rs.getString(2).equals(user.getUsername()) && rs.getString(3).equals(user.getPassword())) {
                    loggedIn = true;
                    id = Integer.toString(rs.getInt(1));
                    break;
                }
            }

            HashMap<String, String> response = new HashMap<>();
            if (loggedIn) response.put("response", id);
            else response.put("response", "Credentials doesn't match");
            return gson.toJson(response);

        } catch (SQLException e) {

            return hc.error(e);
        }
    }

    public Object changeUserYear(int id, int year) {
        try {
            String query = """
                    UPDATE users
                    SET year = ?
                    WHERE id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            return 200;

        } catch (SQLException e) {
            return hc.error(e);
        }
    }

    //TESTING & DEV
    public void deleteAllUsers() {
        try {
            String query = """
                    DELETE FROM users
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Users deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printUsers() {
        try {
            String query = """
                    SELECT * FROM users
                    """;

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getInt(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
