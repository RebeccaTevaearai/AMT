package service;

import data.Article;
import data.CartArticle;

import java.util.ArrayList;

public class CartQueries {

    public ArrayList<CartArticle> getArticles(long idAccount)
    {
        return new ArrayList<>();
    }

    public boolean addArticle(long idAccount, long idArticle, int quantity)
    {
        return false;
    }

    public boolean updateArticle(long idAccount, long idArticle, int quantity)
    {
        return false;
    }

    public boolean deleteArticle(long idAccount, long idArticle)
    {
        return false;
    }
}
