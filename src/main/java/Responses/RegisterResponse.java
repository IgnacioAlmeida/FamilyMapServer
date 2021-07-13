package Responses;

/**
 * A Register Response.
 */
public class RegisterResponse extends Response{
    /**
     * Non-empty auth token string.
     */
    private String authToken;
    /**
     * Username passed in with request.
     */
    private String username;
    /**
     * Person ID of the user's generated Person object.
     */
    private String personID;
    /**
     * Boolean identifier.
     */
    private boolean success;
    /**
     * RegisterResponse Constructor.
     */
    RegisterResponse(){
        super();
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
