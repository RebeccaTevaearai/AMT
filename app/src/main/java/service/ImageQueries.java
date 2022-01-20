package service;

import data.Image;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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

    static public String addImagetoPath(String file, String imageName, String imageType) throws Exception {

        //String path = "./webapp/css/images/" + imageType + "/" + imageName;
        String path = "/" + imageType + "/" + imageName + ".jpg";
        try {
            File f = new File(path);
            FileOutputStream fos = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            //convert string to byte array
            byte[] bytes = file.getBytes();
            //write byte array to file
            bos.write(bytes);
            bos.close();
            fos.close();

        } catch (IOException e) {
            throw new Exception(e);
        }

        return path;

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

    static public void addImage(Long idArticle, String file, String fileType) throws Exception {
        String imageName = fileType + idArticle;
        String path = addImagetoPath(file, imageName, fileType);
        addImageToArticle(idArticle, path);
    }
}
