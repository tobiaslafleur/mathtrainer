package server.database.handlers;

import com.google.gson.Gson;
import model.Answers;
import model.DetailedResults;
import model.NewQuestions;
import server.database.HandlerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailedResultsHandler {

    private Gson gson;
    private Connection connection;
    private HandlerController hc;

    public DetailedResultsHandler(Connection connection, HandlerController hc) {
        this.connection = connection;
        this.hc = hc;
        gson = new Gson();
    }

    public Object getDetailedResults(int resultId) {
        try {
            String query = """
                    SELECT * FROM detailed_results
                    WHERE results_id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, resultId);
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<DetailedResults> detailedResults = new ArrayList<>();
            while(rs.next()) {
                detailedResults.add(new DetailedResults(
                        rs.getInt(1),
                        (NewQuestions) hc.getQuestion(rs.getInt(2)),
                        rs.getInt(3),
                        (Answers) hc.getAnswer(rs.getInt(4)),
                        (Answers) hc.getAnswer(rs.getInt(5))
                ));
            }

            if(detailedResults.isEmpty()) {
                HashMap<String, String> response = new HashMap<>();
                response.put("error", "No results found");
                return gson.toJson(response);
            }

            return gson.toJson(detailedResults);

        } catch (SQLException e) {
            return hc.error(e);
        }
    }
}
