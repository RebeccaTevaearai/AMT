package service;


import data.Category;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryQueries implements CategoryInterface {

    @Override
    public ArrayList<Category> getAllCategory() {
        Category category = null;
        ArrayList<Category> categories = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Category`", new ArrayList<>());

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
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Category` WHERE `name` = ?",
                    new ArrayList<String>() {{add(name);}});

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
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Category` " +
                    "INNER JOIN `isDefineBy` ON `isDefineBy`.`id_Category` = `Category`.`id` WHERE `isDefineBy`.`id_Article` = ?",
                    new ArrayList<String>() {{add(idArticle.toString());}});

            resultSet.next();
            categories.add(new Category(resultSet.getLong("id"), resultSet.getString("name")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
