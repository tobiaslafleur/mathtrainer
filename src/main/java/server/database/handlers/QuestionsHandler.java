package server.database.handlers;

import com.google.gson.Gson;
import model.Answers;
import model.NewQuestions;
import server.database.HandlerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionsHandler {

    private Connection connection;
    private Gson gson;
    private HandlerController hc;

    public QuestionsHandler(Connection connection, HandlerController hc) {
        this.connection = connection;
        this.gson = new Gson();
        this.hc = hc;
    }

    public Object getRandQuestions(int limit, int year) {
        try {
            String query = """
                    SELECT * FROM questions
                    WHERE year = ?
                    ORDER BY RANDOM()
                    LIMIT ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, limit);
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<NewQuestions> questions = new ArrayList<>();
            while(rs.next()) {
                NewQuestions q = new NewQuestions(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        hc.getAnswers(rs.getInt(1)));

                questions.add(q);
            }

            if(questions.isEmpty()) {
                HashMap<String, String> response = new HashMap<>();
                response.put("error", "No questions found");
                return gson.toJson(response);
            } else {
                return gson.toJson(questions);
            }

        } catch (SQLException e) {
            return hc.error(e);
        }
    }

    public Object getCategoryQuestions(int year, int categoryId, int limit) {
        try {
            String query = """
                    SELECT * FROM questions
                    WHERE year = ?
                    AND category_id = ?
                    LIMIT ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.setInt(3, limit);
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<NewQuestions> questions = new ArrayList<>();
            while(rs.next()) {
                NewQuestions q = new NewQuestions(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        hc.getAnswers(rs.getInt(1)));

                questions.add(q);
            }

            if(questions.isEmpty()) {
                HashMap<String, String> response = new HashMap<>();
                response.put("error", "No questions found");
                return gson.toJson(response);
            } else {
                return gson.toJson(questions);
            }

        } catch (SQLException e) {
            return hc.error(e);
        }
    }
}
