package data;

/**
 * Class the represent category
 */
public class Category {

    private long id;
    private String name;

    /**
     * Default constructor
     */
    public Category() {
    }

    /**
     * Constructor
     * @param id Category id
     * @param name Category name
     */
    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get category id
     * @return Category id
     */
    public long getId() {
        return id;
    }

    /**
     * Set category id
     * @param id Category id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get category name
     * @return Category name
     */
    public String getName() {
        return name;
    }

    /**
     * Set category name
     * @param name Category name
     */
    public void setName(String name) {
        this.name = name;
    }
}
