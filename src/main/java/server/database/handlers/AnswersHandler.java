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
}
