package server;

import client.controllers.GameController;
import com.google.gson.Gson;
import model.NewUser;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DBUsers {
    private Connection connection;
    private Gson gson;

    public DBUsers(Connection connection) {
        this.connection = connection;
        this.gson = new Gson();
    }

    public int addUser(String json) {
        NewUser user = gson.fromJson(json, NewUser.class);

        try {
            String query = """
                    INSERT INTO users (id, username, password, year)
                    VALUES (DEFAULT, ?, ?, ?)
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBigDecimal(3, new BigDecimal(user.getYear()));
            preparedStatement.execute();

            return 200;
        } catch (Exception e) {
            System.out.println(e);
            return 500;
        }
    }

    public Object login(String json) {
        NewUser user = gson.fromJson(json, NewUser.class);

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

            if(!loggedIn) {
                return 400;
            } else {
                return 200;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }
}
