package data;

/**
 * Class that represent the account
 */
public class Account {

    private long id;
    private String email;
    private String role;

    /**
     * Default constructor
     */
    public Account() {
    }

    /**
     * Constructor
     * @param id Account id
     * @param email Account email
     * @param role Account role
     */
    public Account(long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    /**
     * Get Account id
     * @return Account id
     */
    public long getId() {
        return id;
    }

    /**
     * Set account id
     * @param id Account id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get account email
     * @return Account email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set account email
     * @param email Account email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get account role
     * @return Account role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set account role
     * @param role Account role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
