package Model;

import Data.Article;

import java.sql.*;

public class ArticleModel {

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

    public Article getArticleById(Long id){

        return new Article();
    }


}
