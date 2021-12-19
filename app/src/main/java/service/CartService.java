package service;

import data.Account;
import data.Article;
import data.CartArticle;

import java.util.ArrayList;


public class CartService {

    private ArrayList<CartArticle> articles;
    private Account account;

    CartService(ArrayList<CartArticle> articles){
        this.articles = articles;
    }

    public ArrayList<CartArticle> getCartArticle()
    {
        return articles;
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

    public ArrayList<CartArticle> addArticle(Article article, int quantity)
    {
        //TODO verif si pas déjà dans panier
        articles.add(new CartArticle(article,quantity));
        return articles;
    }

    public ArrayList<CartArticle> deleteArticle(Article article){
        //TODO
        return articles;
    }

    public ArrayList<CartArticle> changeQuantity(Article article, int quantity)
    {
        //TODO
        return articles;
    }
}
