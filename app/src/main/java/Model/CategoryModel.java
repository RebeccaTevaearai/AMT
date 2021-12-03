package Model;

import Data.Article;
import Data.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryModel implements CategoryInterface {

    @Override
    public ArrayList<Category> getAllCategory() {
        Category category = null;
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM `category`");
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()){
                category = new Category(resultSet.getLong("id"), resultSet.getString("name"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM `category`");
            ResultSet resultSet = st.executeQuery();

            category = new Category(resultSet.getLong("id"), resultSet.getString("name"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
