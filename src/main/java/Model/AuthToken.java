package Model;

import java.util.Objects;

/**
 * An Authorization Token.
 */
public class AuthToken {
    /**
     * userName that is using the token.
     */
    private String username;

    /**
     * The Token being created for security reasons.
     */
    private String token;

    /**
     * Creates an authorization token.
     *
     * @param username
     * @param token
     */
    public AuthToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(username, authToken.username) && Objects.equals(token, authToken.token);
    }
}
