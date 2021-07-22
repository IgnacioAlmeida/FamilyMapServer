package Responses;

/**
 * A Register Response.
 */
public class RegisterResponse extends Response{
    /**
     * Non-empty auth token string.
     */
    private String authtoken;
    /**
     * Username passed in with request.
     */
    private String username;
    /**
     * Person ID of the user's generated Person object.
     */
    private String personID;
    /**
     * RegisterResponse Constructor.
     */
    public RegisterResponse(){
        super();
    }

    public RegisterResponse(String message){
        super(message);
    }

    public RegisterResponse(String authtoken, String username, String personID){
        super();
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
    }

    public String getAuthToken() {
        return authtoken;
    }

    public void setAuthToken(String authtoken) {
        this.authtoken = authtoken;
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
