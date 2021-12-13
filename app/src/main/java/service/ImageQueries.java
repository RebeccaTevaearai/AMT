package service;

import data.Image;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ImageQueries implements ImageInterface {

    @Override
    public ArrayList<Image> getImageByArticleId(Long idArticle)
    {
        ArrayList<Image> images = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Image` WHERE `id_Article` = ?",
                    new ArrayList<String>() {{add(idArticle.toString());}});

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
