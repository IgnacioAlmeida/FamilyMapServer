package Responses;

/**
 * A Login Response.
 */
public class LoginResponse extends Response{
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
     * LoginResponse constructor.
     */
    public LoginResponse(){
        super();
    }

    public LoginResponse(String message){
        super(message);
    }

    public LoginResponse(String authtoken, String username, String personID){
        super();
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
    }


    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
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

}
