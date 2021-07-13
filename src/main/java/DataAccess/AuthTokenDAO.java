package DataAccess;

import Model.AuthToken;

public class AuthTokenDAO {
    /**
     * Creates a new Authorization Token to data
     * @param authToken
     */
    public void create(AuthToken authToken){

    }

    /**
     * Deletes an Authorization Token from the data
     * @param authToken
     */
    public void delete(String authToken){

    }

    /**
     * Gets an Authorization Token from the data with a given userID
     * @param userID
     * @return user
     */
    public AuthToken get(String userID){
        AuthToken authToken = null;
        return authToken;
    }

    /**
     * Gets back the given Authorization Token
     * @param authToken
     * @return newAuthToken
     */
    public AuthToken getAuthToken(String authToken){
        AuthToken newAuthToken = null;
        return newAuthToken ;
    }

    /**
     * Deletes all the Authorization Tokens from the table
     */
    public void clear(){

    }
}
