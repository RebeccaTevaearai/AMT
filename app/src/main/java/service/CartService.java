package service;

import data.Account;
import data.Article;
import data.CartArticle;

import java.util.ArrayList;


public class CartService {

    private ArrayList<CartArticle> articles;
    private Account account = null;

    public CartService(ArrayList<CartArticle> articles){
        this.articles = articles;
    }

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

    public int articlesQuantity()
    {
        int total = 0;
        for(CartArticle articleQuantity : articles)
        {
            total += articleQuantity.getQuantity();
        }
        return total;
    }

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

    public ArrayList<CartArticle> getArticles() {
        return articles;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
