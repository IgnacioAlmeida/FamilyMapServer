package Requests;

/**
 * A Login Request.
 */
public class LoginRequest {
    /**
     * The user's Username.
     */
    private String username;
    /**
     * The Username's password.
     */
    private String password;

    /**
     * Empty constructor for GSON
     */
    public LoginRequest() {
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
