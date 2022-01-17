package service;

import data.Article;
import data.Category;
import data.Image;

import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;

/**
 * Articles related SQL queries
 */
public class ArticleQueries implements ArticleInterface{

    /**
     * Get article by id
     * @param id Article id
     * @return The aticle
     */
    @Override
    public Article getArticleById(Long id){
        Article article = null;
        try {
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Article` WHERE `id` = ?",
                new ArrayList<String>() {{ add(id.toString()); }});

            resultSet.next();

            long idArticle = resultSet.getLong("Article.id");
            ArrayList<Image> images  = new ImageQueries().getImageByArticleId(idArticle);

            ArrayList<Category> articleCategories = new CategoryQueries().getCategoryByArticleId(idArticle);

            article = new Article(idArticle,resultSet.getString("name")
                    ,resultSet.getString("description"),resultSet.getLong("quantity")
                    ,resultSet.getDouble("price"), articleCategories, images);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return article;
    }

    /**
     * Get articles by categories
     * @param categories Categories array to use as a filter
     * @return Articles list
     */
    @Override
    public ArrayList<Article> getArticleByCatergories(ArrayList<Category> categories) {

        ArrayList<Article> articles = new ArrayList<>();
        try {
            String query = "SELECT * FROM `Article` ";

            if(!categories.isEmpty())
                query += "INNER JOIN `isDefineBy` ON `isDefineBy`.`id_Article` = `Article`.`id` WHERE";

            boolean first = true;
            for(Category category : categories)
            {
                if(first){
                    query += "`isDefineBy`.`id_Category` = " + category.getId();
                    first = false;
                }else{
                    query += "OR `isDefineBy`.`id_Category` = " + category.getId();
                }
            }

            ResultSet resultSet = DatabaseConnection.doQuery(query,new ArrayList<String>());

            while (resultSet.next()){
                long idArticle = resultSet.getLong("Article.id");
                ArrayList<Image> images  = new ImageQueries().getImageByArticleId(idArticle);

                ArrayList<Category> articleCategories = new CategoryQueries().getCategoryByArticleId(idArticle);

                articles.add(new Article(idArticle,resultSet.getString("Article.name")
                        ,resultSet.getString("description"),resultSet.getLong("quantity")
                        ,resultSet.getDouble("price"), articleCategories, images));
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return articles;
    }

    /**
     * add a Category to an article
     * @param idArticle Article's id
     * @param idCategory Category's id
     * @return Articles list
     */
    public boolean addCategoryToArticle(Long idArticle, Long idCategory)
    {
        try {
            DatabaseConnection.doQueryUpdate("INSERT INTO `isDefineBy` " +
                    "(`id_Article`, `id_Category`) VALUES (?, ?);",
                    new ArrayList<>()
                    {{
                        add(idArticle.toString());
                        add(idCategory.toString());
                    }}
            );

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
