package server.database.handlers;

import com.google.gson.Gson;
import model.Questions;
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

            ArrayList<Questions> questions = new ArrayList<>();
            while(rs.next()) {
                Questions q = new Questions(
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
                    ORDER BY RANDOM()
                    LIMIT ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.setInt(3, limit);
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Questions> questions = new ArrayList<>();
            while(rs.next()) {
                Questions q = new Questions(
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

    public Object getQuestion(int id) {
        try {
            String query = """
                    SELECT * FROM questions
                    WHERE id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Questions question = null;
            while(rs.next()) {
                question = new Questions(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4));
            }

            return question;

        } catch (SQLException e) {
            return hc.error(e);
        }
    }

    //TESTING AND DEV
    public void printQuestions() {
        try {
            String query = """
                    SELECT * FROM questions
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getInt(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getInt(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addQuestion(int category, String question, int year) {
        try {
            String query = """
                    INSERT INTO questions (id, category_id, question, year)
                    VALUES (DEFAULT, ?, ?, ?);
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, category);
            preparedStatement.setString(2, question);
            preparedStatement.setInt(3, year);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeQuestion(String newQuestion) {
        try {
            String query = """
                    UPDATE questions
                    SET question = ?
                    WHERE id = 2
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newQuestion);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
