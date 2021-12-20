package service;

import data.Article;
import data.Category;
import data.Image;

import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;

public class ArticleQueries implements ArticleInterface{

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
}
