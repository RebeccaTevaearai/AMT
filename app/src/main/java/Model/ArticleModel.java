package Model;

import Data.Article;
import Data.Category;

import java.sql.*;
import java.util.ArrayList;

public class ArticleModel implements ArticleInterface{

    public Article getArticleByName(String name) throws SQLException {
        //TODO connection BD
        Article article = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM `article` WHERE name = ?");
            st.setString(1,name);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()){
                article = new Article(resultSet.getLong("id"),resultSet.getString("name")
                        ,resultSet.getString("description"),resultSet.getLong("quantity")
                        ,resultSet.getDouble("price"));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return article;
    }


    @Override
    public ArrayList<Article> getArticleByCatergories(Category[] categories) {
        return new ArrayList<>();
    }
}
