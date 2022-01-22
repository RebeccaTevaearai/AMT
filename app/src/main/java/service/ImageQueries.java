package service;

import data.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Image related SQL queries
 */
public class ImageQueries implements ImageInterface {

    /**
     * Get article images
     *
     * @param idArticle Article id
     * @return List of images
     */
    @Override
    public ArrayList<Image> getImageByArticleId(Long idArticle) {
        ArrayList<Image> images = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseConnection.doQuery("SELECT * FROM `Image` WHERE `id_Article` = ?",
                    new ArrayList<String>() {{
                        add(idArticle.toString());
                    }});

            while (resultSet.next()) {
                images.add(new Image(resultSet.getLong("id"), resultSet.getString("path")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return images;
    }

    /**
     * @param idArticle : the id of the Article to whom the file will be associate
     * @param imagePath : the complete path of the image (must exit)
     * @throws Exception
     */
    static public void addImageToArticle(Long idArticle, String imagePath) throws Exception {
        try {

            String sql = "INSERT INTO `Image` (path, id_Article) values (?, ?)";

            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(sql);
            st.setString(1, imagePath);
            st.setString(2, String.valueOf(idArticle));

            st.executeUpdate();

        } catch (Exception e) {
            throw new Exception();
        }
    }
}
