package Model;

import Data.Article;
import Data.Category;

import java.util.ArrayList;

public interface ArticleInterface {
    /***
     * @param categories Tableau des catégories à filter
     * @return Un tableau avec tous les articles qui possède au moins une des catégories
     */
    public ArrayList<Article> getArticleByCatergories(ArrayList<Category> categories);
}
