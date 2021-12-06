package Model;

import Data.Image;

import java.util.ArrayList;

public interface ImageInterface {
    /***
     * @param idArticle
     * @return Toutes les images d'un article
     */
    public ArrayList<Image> getImageByArticleId(Long idArticle);
}
