package server.database.handlers;

import com.google.gson.Gson;
import model.Category;
import server.database.HandlerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Object getCategoryObj(int id) {
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

    public Object getCategoryJson(int id) {
        Category category = (Category) getCategoryObj(id);

        if(category == null) {
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "No answer found");
            return gson.toJson(response);
        }

        return gson.toJson(category);
    }

    public Object getAllCategories() {
        try {
            String query = """
                    SELECT * FROM categories
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<Category> categories = new ArrayList<>();
            while(rs.next()) {
                categories.add(new Category(rs.getInt(1), rs.getString(2)));
            }

            if(categories.isEmpty()) {
                HashMap<String, String> response = new HashMap<>();
                response.put("error", "No categories found");
                return gson.toJson(response);
            }

            return gson.toJson(categories);

        } catch (SQLException e) {
            return hc.error(e);
        }
    }

    //TESTING & DEV
    public void addCategory(int id, String category) {
        try {
            String query = """
                    INSERT INTO categories (id, category)
                    VALUES (?, ?)
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, category);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
