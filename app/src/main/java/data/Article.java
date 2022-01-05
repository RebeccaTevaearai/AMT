package data;

import java.util.ArrayList;

/**
 * Class that represent article
 */
public class Article {

    private long id;
    private String name;
    private String description;
    private long quantity;
    private double price;
    private ArrayList<Image> images;
    private ArrayList<Category> categories;

    /**
     * Constructor
     * @param id Article id
     * @param name Article name
     * @param description Article description
     * @param quantity Article quantity
     * @param price Article price
     * @param categories Article categories
     * @param images Article images
     */
    public Article(long id, String name, String description, long quantity, double price, ArrayList<Category> categories, ArrayList<Image> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.images = images;
        this.categories = categories;
    }

    /**
     * Default constructor
     */
    public Article() {
    }

    /**
     * Get article id
     * @return The id
     */
    public long getId() {
        return id;
    }

    /**
     * Set article id
     * @param id Article id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get article name
     * @return Article name
     */
    public String getName() {
        return name;
    }

    /**
     * Set article name
     * @param name Article name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get article price
     * @return Article price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set article price
     * @param price Article price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get article description
     * @return Article description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set article description
     * @param description Article description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get article quantity
     * @return Article quantity
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Set article quantity
     * @param quantity Article quantity
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    /**
     * Get article categories
     * @return Article categories list
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     * Set article categories
     * @param categories List of categories
     */
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    /**
     * Get article images
     * @return Article images list
     */
    public ArrayList<Image> getImages() {
        return images;
    }

    /**
     * Set article images
     * @param images List of images
     */
    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}
