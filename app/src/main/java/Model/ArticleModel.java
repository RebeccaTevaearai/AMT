package Model;

import Data.Article;
import Data.Category;
import Data.Image;

import java.sql.*;
import java.util.ArrayList;

public class ArticleModel implements ArticleInterface{

    public Article getArticleByName(String name) throws SQLException {
        //TODO connection BD
        Article article = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM `Article` WHERE name = ?");
            st.setString(1,name);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()){
                article = new Article(resultSet.getLong("id"),resultSet.getString("name")
                        ,resultSet.getString("description"),resultSet.getLong("quantity")
                        ,resultSet.getDouble("price"),new ArrayList<>(), new ArrayList<>());
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return article;
    }

    public Article getArticleById(Long id){

        return new Article();
    }


    @Override
    public ArrayList<Article> getArticleByCatergories(ArrayList<Category> categories) {

        ArrayList<Article> articles = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
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

            PreparedStatement st = connection.prepareStatement(query);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()){
                long idArticle = resultSet.getLong("Article.id");
                ArrayList<Image> images  = new ImageModel().getImageByArticleId(idArticle);

                ArrayList<Category> articleCategories = new CategoryModel().getCategoryByArticleId(idArticle);

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
