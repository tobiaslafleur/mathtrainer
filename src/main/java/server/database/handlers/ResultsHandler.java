package server.database.handlers;

import com.google.gson.Gson;
import model.Category;
import model.Results;
import server.database.HandlerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultsHandler {

    private Connection connection;
    private Gson gson;
    private HandlerController hc;

    public ResultsHandler(Connection connection, HandlerController hc) {
        this.connection = connection;
        this.hc = hc;
        gson = new Gson();
    }

    public Object addResult(int user, String body) {
        Results result = gson.fromJson(body, Results.class);
        try {
            String query = """
                    INSERT INTO results (id, user_id, category_id, grade, score, maximum_score)
                    VALUES (DEFAULT, ? ,?, ?, ?, ?)
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user);
            preparedStatement.setInt(2, result.getCategoryId().getId());
            preparedStatement.setString(3, String.valueOf(result.getGrade()));
            preparedStatement.setInt(4, result.getScore());
            preparedStatement.setInt(5, 10);
            preparedStatement.executeUpdate();

            return 200;
        }
        catch (SQLException e) {
            return hc.error(e);
        }
    }

    public Object getResults(int user) {
        try {
            String query = """
                    SELECT * FROM results
                    WHERE user_id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user);
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Results> results = new ArrayList<>();
            while(rs.next()) {
                results.add(new Results(
                        rs.getInt(1),
                        rs.getInt(2),
                        (Category) hc.getCategoryObj(rs.getInt(3)),
                        rs.getString(4).toCharArray()[0],
                        rs.getInt(5),
                        rs.getInt(6)));

            }

            if(results.isEmpty()) {
                HashMap<String, String> response = new HashMap<>();
                response.put("error", "No results found");
                return gson.toJson(response);
            }

            return gson.toJson(results);

        } catch (SQLException e) {
            return hc.error(e);
        }
    }
}
