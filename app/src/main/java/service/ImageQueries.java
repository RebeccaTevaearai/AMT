package service;

import data.Image;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Image related SQL queries
 */
public class ImageQueries implements ImageInterface {

    /**
     * Get article images
     * @param idArticle Article id
     * @return List of images
     */
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
