package service;

import data.Category;
import java.util.ArrayList;

/**
 * Category inteface
 */
public interface CategoryInterface {
    /***
     * @return toutes les categories null si vide
     */
    public ArrayList<Category> getAllCategory();

    /***
     * @param name
     * @return la category null si vide
     */
    public Category getCategoryByName(String name);

    /**
     * @param idArticle Id de l'article
     * @return Toutes les categories d'un article
     */
    public ArrayList<Category> getCategoryByArticleId(Long idArticle);
}
