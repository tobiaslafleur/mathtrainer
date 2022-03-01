package server.database.handlers;

import com.google.gson.Gson;
import model.Answers;
import server.database.HandlerController;
import spark.Spark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AnswersHandler {

    private Connection connection;
    private HandlerController hc;
    private Gson gson;

    public AnswersHandler(Connection connection, HandlerController hc) {
        this.connection = connection;
        this.hc = hc;
        gson = new Gson();
        initEntryPoints();
    }

    private void initEntryPoints() {
        Spark.get("/:id/answers", (req, res) -> {
            res.header("Content-Type", "application/json");
            return null;
        });
    }

    public ArrayList<Answers> getAnswers(int id) {
        try {
            String query = """
                    SELECT * FROM answers
                    WHERE question_id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Answers> answers = new ArrayList<>();
            while(rs.next()) {
                answers.add(new Answers(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getBoolean(4)));
            }

            return answers;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getAnswerObj(int id) {
        try {
            String query = """
                    SELECT * FROM answers
                    WHERE id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Answers answer = null;

            while(rs.next()) {
                answer = new Answers(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getBoolean(4));
            }


            return answer;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getAnswerJson(int id) {
        Answers answer = (Answers) getAnswerObj(id);
        if(answer == null) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "No answer found");
            return gson.toJson(response);
        }

        return gson.toJson(answer);
    }

    //TESTING & DEV
    public void addAnswer(int questionId, String answer, boolean correct) {
        try {
            String query = """
                    INSERT INTO answers (id, question_id, answer, correct)
                    VALUES (DEFAULT, ?, ?, ?)
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, questionId);
            preparedStatement.setString(2, answer);
            preparedStatement.setBoolean(3, correct);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllAnswers() {
        try {
            String query = """
                    SELECT * FROM answers
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getInt(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getBoolean(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeAnswer() {
        try {
            String query = """
                    UPDATE answers
                    SET question_id = 81, answer = 2
                    WHERE id = 324
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
