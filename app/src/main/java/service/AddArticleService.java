package service;

import javax.servlet.http.Part;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddArticleService {

    /**
     *
     * @param description
     * @return
     */
    static public Boolean verifyDescription(String description) {
        return description != null && description.length() <= 256;
    }

    /**
     * verify if the given price is positif and below a max price
     * @param price
     * @return
     */
    static public Boolean verifyPrice(String price) {

        try {
            int val = Integer.parseInt(price);
            return val >= 0 && val <= 10000000;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verify a given quantity is positive and below a max quantity
     * @param quantity
     * @return
     */
    static public Boolean verifyQuantity(String quantity) {
        try {
            int val = Integer.parseInt(quantity);
            return val >= 0 && val <= 10000000;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param name
     * @return
     */
    static public Boolean verifyName(String name) {
        return name != null && name.length() <= 256;
    }

    /**
     *
     * @param image
     * @return
     */
    static public Boolean verifyImage(String image) {
        return false;
    }

    /**
     * Add a new article row in the database
     * @param name
     * @param description
     * @param price
     * @param quantity
     * @return long id : the id of the new Article
     * @throws Exception
     */
    static public long addArticle(String name,
                                  String description,
                                  String price,
                                  String quantity) throws Exception
    {
        try {
            String sql = "INSERT INTO `Article` (name, description, quantity, price) values (?, ?, ?, ?)";

            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setString(3, quantity);
            st.setString(4, price);

            st.executeUpdate();

            ResultSet resultSet = DatabaseConnection.doQuery(
                    "SELECT * FROM `Article` WHERE `description`= ?",
                    new ArrayList<String>() {{add(description);}}
            );

            // check if not empty
            if(resultSet.next()) {
                // return the id
                return resultSet.getLong("id");
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static boolean addCategoryToArticle(Long idArticle, Long idCategory)
    {
        return new ArticleQueries().addCategoryToArticle(idArticle,idCategory);
    }

    // https://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
    public static String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
