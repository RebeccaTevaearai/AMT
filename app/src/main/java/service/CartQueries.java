package service;

import data.Article;
import data.CartArticle;

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
    public ArrayList<CartArticle> getArticles(long idAccount)
    {
        return new ArrayList<>();
    }

    /**
     * Add article to cart
     * @param idAccount Account id
     * @param idArticle Article id
     * @param quantity Quantity
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean addArticle(long idAccount, long idArticle, int quantity)
    {
        return false;
    }

    /**
     * Update article entry
     * @param idAccount Account id
     * @param idArticle Article id
     * @param quantity New quantity
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean updateArticle(long idAccount, long idArticle, int quantity)
    {
        return false;
    }

    /**
     * Delete article from cart
     * @param idAccount Account id
     * @param idArticle Article id
     * @return A boolean that indicated if the process was successful or not
     */
    public boolean deleteArticle(long idAccount, long idArticle)
    {
        return false;
    }
}
