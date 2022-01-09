package service;

import com.mysql.cj.Session;
import data.Account;
import data.Article;
import data.CartArticle;

import java.util.ArrayList;

/**
 * Class that implements cart actions
 */
public class CartService {

    private ArrayList<CartArticle> articles;
    private Account account = null;

    /**
     * Constructor
     * @param articles List of articles
     */
    public CartService(ArrayList<CartArticle> articles){
        this.articles = articles;
    }

    /**
     *
     */
    public boolean sync()
    {
        if(account != null) {
            AccountQueries accountQueries = new AccountQueries();
            if (accountQueries.getAccountById(account.getId()) == null) {
                accountQueries.createAccount(account.getId(), account.getEmail(), account.getRole());
            } else {
                articles = new CartQueries().getArticles(account.getId());
            }
        }
        return true;
    }

    /**
     * Compute total cart price
     * @return Total price
     */
    public double total()
    {
        double total = 0;
        for(CartArticle articleQuantity : articles)
        {
            total += articleQuantity.getArticle().getPrice()
                    * articleQuantity.getQuantity();
        }
        return total;
    }

    /**
     * Compute total articles
     * @return Total of articles
     */
    public int articlesQuantity()
    {
        int total = 0;
        for(CartArticle articleQuantity : articles)
        {
            total += articleQuantity.getQuantity();
        }
        return total;
    }

    /**
     *  Add article to the list
     * @param article Article to add
     * @param quantity Quantity
     * @return Updated articles list
     */
    public ArrayList<CartArticle> addArticle(Article article, int quantity)
    {
        for (CartArticle cartArticle : articles)
        {
            if(cartArticle.getArticle().getId() == article.getId())
            {
                return updateQuantity(article,cartArticle.getQuantity() + quantity);
            }
        }

        articles.add(new CartArticle(article,quantity));
        if(!(account == null))
        {
            new CartQueries().addArticle(account.getId(),article.getId(),quantity);
        }
        return articles;
    }

    /**
     * Delete article from the list
     * @param article Article to delete
     * @return Updated articles list
     */
    public ArrayList<CartArticle> deleteArticle(Article article){
        for (CartArticle cartArticle : articles)
        {
            if(cartArticle.getArticle().getId() == article.getId())
            {
                articles.remove(cartArticle);
                break;
            }
        }

        if(!(account == null))
        {
            new CartQueries().deleteArticle(account.getId(),article.getId());
        }
        return articles;
    }

    /**
     * Update quantity for an article
     * @param article The article to update quantity to
     * @param quantity New quantity
     * @return Updated articles list
     */
    public ArrayList<CartArticle> updateQuantity(Article article, int quantity)
    {
        for (CartArticle cartArticle : articles)
        {
            if(cartArticle.getArticle().getId() == article.getId())
            {
                cartArticle.setQuantity(quantity);
                break;
            }
        }

        if(!(account == null))
        {
            new CartQueries().updateArticle(account.getId(),article.getId(),quantity);
        }
        return articles;
    }

    /**
     * Get the list of articles
     * @return Articles list
     */
    public ArrayList<CartArticle> getArticles() {
        return articles;
    }

    /**
     * Get user account
     * @return User account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Set account to cart
     * @param account Account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
