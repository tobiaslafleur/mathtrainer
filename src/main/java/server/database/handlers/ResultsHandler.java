package server.database.handlers;

import com.google.gson.Gson;
import model.Results;
import server.database.HandlerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                    VALUES (DEFAULT, ?, ?, ?, ?, ?)
                    RETURNING id;
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user);
            preparedStatement.setInt(2, result.getCategoryId());
            preparedStatement.setString(3, String.valueOf(result.getGrade()));
            preparedStatement.setInt(4, result.getScore());
            preparedStatement.setInt(5, result.getMaxScore());
            ResultSet rs = preparedStatement.executeQuery();

            int id = -1;
            while(rs.next()) {
                id = rs.getInt(1);
            }

            if(id < 0) return "Error";





        } catch (SQLException e) {
            return hc.error(e);
        }

        return null;
    }
}
