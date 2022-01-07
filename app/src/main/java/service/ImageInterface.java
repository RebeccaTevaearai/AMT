package service;

import data.Image;

import java.util.ArrayList;

/**
 * Image interface
 */
public interface ImageInterface {
    /***
     * @param idArticle
     * @return Toutes les images d'un article
     */
    public ArrayList<Image> getImageByArticleId(Long idArticle);
}
