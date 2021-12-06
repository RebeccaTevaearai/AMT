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
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM `Category`");
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
            PreparedStatement st = connection.prepareStatement("SELECT * FROM `Category` WHERE `name` = ?");
            st.setString(1,name);
            ResultSet resultSet = st.executeQuery();

            resultSet.next();
            category = new Category(resultSet.getLong("id"), resultSet.getString("name"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public ArrayList<Category> getCategoryByArticleId(Long idArticle) {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM `Category` " +
                    "INNER JOIN `isDefineBy` ON `isDefineBy`.`id_Category` = `Category`.`id` WHERE `isDefineBy`.`id_Article` = ?");
            st.setString(1, idArticle.toString());
            ResultSet resultSet = st.executeQuery();

            resultSet.next();
            categories.add(new Category(resultSet.getLong("id"), resultSet.getString("name")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
