package Model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(userName, authToken.userName) && Objects.equals(token, authToken.token);
    }
}
