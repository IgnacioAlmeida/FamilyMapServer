package Model;

/**
 * An Authorization Token.
 */
public class AuthToken {
    /**
     * userName that is using the token.
     */
    private String userName;

    /**
     * The Token being created for security reasons.
     */
    private String token;

    /**
     * Creates an authorization token.
     *
     * @param userName
     * @param token
     */
    public AuthToken(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
