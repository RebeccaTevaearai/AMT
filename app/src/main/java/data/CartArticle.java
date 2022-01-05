package data;

/**
 * Article in the cart
 */
public class CartArticle {
    private Article article;
    private int quantity;

    /**
     * Constructor
     * @param article Article
     * @param quantity Quantity
     */
    public CartArticle(Article article, int quantity) {
        this.article = article;
        this.quantity = quantity;
    }

    /**
     * Get the article
     * @return The article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * Set article
     * @param article The article
     */
    public void setArticle(Article article) {
        this.article = article;
    }

    /**
     * Get the article quantity
     * @return Quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity
     * @param quantity Quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
