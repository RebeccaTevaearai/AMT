package service;

import data.Article;
import data.CartArticle;
import data.Category;
import data.Image;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Cart related SQL queries
 */
public class CartQueries {

    /**
     * Get cart articles
     * @param idAccount Account id
     * @return Articles list
     */
    public ArrayList<CartArticle> getArticles(Long idAccount)
    {
        ArrayList<CartArticle> articles = new ArrayList<>();
        try {
            String query = "SELECT * FROM `isInCartOf` INNER JOIN `Article` ON `isInCartOf`.`id_Article` = `Article`.`id`" +
                    "WHERE `isInCartOf`.`id_Account` = ?";

            ResultSet resultSet = DatabaseConnection.doQuery(query, new ArrayList<>() {{
                add(idAccount.toString());
            }});

            while (resultSet.next()){
                long idArticle = resultSet.getLong("Article.id");
                ArrayList<Image> images  = new ImageQueries().getImageByArticleId(idArticle);

                ArrayList<Category> articleCategories = new CategoryQueries().getCategoryByArticleId(idArticle);

                articles.add(new CartArticle(new Article(idArticle,resultSet.getString("Article.name")
                        ,resultSet.getString("description"),resultSet.getLong("quantity")
                        ,resultSet.getDouble("price"),articleCategories,images),resultSet.getInt("Quantity")));
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return articles;
    }

    /**
     * Add article to cart
     * @param idAccount Account id
     * @param idArticle Article id
     * @param quantity Quantity
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean addArticle(Long idAccount, Long idArticle, Integer quantity)
    {
        try {
            String query = "INSERT INTO `isInCartOf` (`id_Account`, `id_Article`, `Quantity`) VALUES (?, ?, ?);";

            DatabaseConnection.doQueryUpdate(query, new ArrayList<>() {{
                add(idAccount.toString());
                add(idArticle.toString());
                add(quantity.toString());
            }});

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Update article entry
     * @param idAccount Account id
     * @param idArticle Article id
     * @param quantity New quantity
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean updateArticle(Long idAccount, Long idArticle, Integer quantity)
    {
        try {
            String query = "UPDATE `isInCartOf` SET `Quantity`=? WHERE  `id_Account`=? AND `id_Article`=?;";

            DatabaseConnection.doQueryUpdate(query, new ArrayList<>() {{
                add(quantity.toString());
                add(idAccount.toString());
                add(idArticle.toString());
            }});

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Delete article from cart
     * @param idAccount Account id
     * @param idArticle Article id
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean deleteArticle(Long idAccount, Long idArticle)
    {
        try {
            String query = "DELETE FROM `isInCartOf` WHERE  `id_Account`=? AND `id_Article`=?;";

            DatabaseConnection.doQueryUpdate(query, new ArrayList<>() {{
                add(idAccount.toString());
                add(idArticle.toString());
            }});

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }
}
