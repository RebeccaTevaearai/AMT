package Model;

import Data.Article;
import Data.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ImageModel {

    public ArrayList<Image> getImageByArticleId(Long article_id)
    {
        ArrayList<Image> images = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM `Image` WHERE `id_Article` = ?");
            st.setString(1,article_id.toString());
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()){
                images.add(new Image(resultSet.getLong("id"),resultSet.getString("path")));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return images;
    }

}
