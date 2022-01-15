package service;


import data.Account;
import data.Category;

import javax.servlet.http.PushBuilder;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Category related SQL queries
 */
public class CategoryQueries implements CategoryInterface {

    /**
     * Get all categories
     * @return Categories list
     */
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

    /**
     * Get category by name
     * @param name Category name
     * @return Category
     */
    @Override
    public Category getCategoryByName(String name) {
        Category category = null;
        try {
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Category` WHERE `name` = ?",
                    new ArrayList<String>() {{add(name);}});

            if(resultSet.next())
                category = new Category(resultSet.getLong("id"), resultSet.getString("name"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * Get category by name
     * @param id Category id
     * @return Category
     */
    public Category getCategoryById(Long id) {
        Category category = null;
        try {
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Category` WHERE `id` = ?",
                    new ArrayList<String>() {{add(id.toString());}});

            if(resultSet.next())
                category = new Category(resultSet.getLong("id"), resultSet.getString("name"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * Get article categories.
     * @param idArticle Article id
     * @return Categories list
     */
    @Override
    public ArrayList<Category> getCategoryByArticleId(Long idArticle) {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Category` " +
                    "INNER JOIN `isDefineBy` ON `isDefineBy`.`id_Category` = `Category`.`id` WHERE `isDefineBy`.`id_Article` = ?",
                    new ArrayList<String>() {{add(idArticle.toString());}});

            while(resultSet.next())
                categories.add(new Category(resultSet.getLong("id"), resultSet.getString("name")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Create a category.
     * @param name category's name
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean createCategory(String name)
    {
        try {
            DatabaseConnection.doQueryUpdate("INSERT INTO `Category` (`name`) VALUES (?);",
                    new ArrayList<String>() {{ add(name); }});

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Update a category.
     * @param id category's id
     * @param name category's new name
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean updateCategory(Long id, String name)
    {
        try {
            DatabaseConnection.doQueryUpdate("UPDATE `Category` SET `name`=? WHERE id=?;",
                    new ArrayList<String>() {{ add(id.toString()); add(name); }});

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Delete a category.
     * @param id category's id
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean deleteCategory(Long id)
    {
        try {
            DatabaseConnection.doQueryUpdate("DELETE FROM `Category` WHERE id=?;",
                    new ArrayList<String>() {{ add(id.toString()); }});

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
