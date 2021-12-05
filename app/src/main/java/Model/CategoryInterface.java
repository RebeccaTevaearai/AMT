package Model;

import Data.Category;
import java.util.ArrayList;

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
}
