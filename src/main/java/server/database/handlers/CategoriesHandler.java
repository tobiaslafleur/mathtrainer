package server.database.handlers;

import com.google.gson.Gson;
import model.Category;
import server.database.HandlerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CategoriesHandler {
    private Connection connection;
    private Gson gson;
    private HandlerController hc;

    public CategoriesHandler(Connection connection, HandlerController hc) {
        this.connection = connection;
        this.gson = new Gson();
        this.hc = hc;
    }

    //TODO: Get categories
    public Object getCategoryAsObj(int id) {
        try {
            String query = """
                    SELECT * FROM categories
                    WHERE id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Category category = null;
            while(rs.next()) {
                category = new Category(rs.getInt(1), rs.getString(2));
            }

            if(category == null) {
                HashMap<String, String> response = new HashMap<>();
                response.put("error", "No categories found");
                return null;
            }

            return category;

        } catch (SQLException e) {
            return null;
        }
    }

    public Object getCategory(int id) {
        try {
            String query = """
                    SELECT * FROM categories
                    WHERE id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Category category = null;
            while(rs.next()) {
                category = new Category(rs.getInt(1), rs.getString(2));
            }

            if(category == null) {
                HashMap<String, String> response = new HashMap<>();
                response.put("error", "No categories found");
                return gson.toJson(response);
            }

            return gson.toJson(category);

        } catch (SQLException e) {
            return hc.error(e);
        }
    }
}
