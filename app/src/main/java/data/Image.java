package data;

/**
 * Class the represent image
 */
public class Image {

    private long id;
    private String path;

    /**
     * default constructor
     */
    public Image() {
    }

    /**
     * Constructor
     * @param id Image id
     * @param path image path
     */
    public Image(long id, String path) {
        this.id = id;
        this.path = path;
    }

    /**
     * Get image id
     * @return Image id
     */
    public long getId() {
        return id;
    }

    /**
     * Set image id
     * @param id Image id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get image path
     * @return Image path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set image path
     * @param path Image path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
