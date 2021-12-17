package service;

import data.Account;
import data.Article;

import java.util.ArrayList;


public class CartService {

    public class ArticleQuantity{
        public Article article;
        public int quantity;

        ArticleQuantity(Article article, int quantity){
            this.article = article;
            this.quantity = quantity;
        }
    }

    private ArrayList<ArticleQuantity> articles;
    private Account account;

    CartService(ArrayList<ArticleQuantity> articles){
        this.articles = articles;
    }

    public ArrayList<ArticleQuantity> getCartArticle()
    {
        return articles;
    }

    public double total()
    {
        double total = 0;
        for(ArticleQuantity articleQuantity : articles)
        {
            total += articleQuantity.article.getPrice() * articleQuantity.quantity;
        }
        return total;
    }

    public ArrayList<ArticleQuantity> addArticle(Article article, int quantity)
    {
        //TODO verif si pas déjà dans panier
        articles.add(new ArticleQuantity(article,quantity));
        return articles;
    }

    public ArrayList<ArticleQuantity> deleteArticle(Article article){
        //TODO
        return articles;
    }

    public ArrayList<ArticleQuantity> changeQuantity(Article article, int quantity)
    {
        //TODO
        return articles;
    }
}
