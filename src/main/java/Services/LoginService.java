package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Model.AuthToken;
import Model.User;
import Requests.LoginRequest;
import Responses.LoginResponse;
import Responses.Response;

import java.sql.Connection;

/**
 * A Login Service.
 */
public class LoginService {

    /**
     * Logs in the user and returns its request body.
     * @param loginRequest
     * @return response
     */
    public LoginResponse login(LoginRequest loginRequest) throws DataAccessException {
        LoginResponse response = null;
        Database db = new Database();
        String requestedUsername = loginRequest.getUserName();
        String requestedPassword = loginRequest.getPassword();
        AuthToken authToken = null;

        try {
            AuthTokenDAO aDAO = new AuthTokenDAO(db.getConnection());
            UserDAO uDAO = new UserDAO(db.getConnection());
            User systemUser = uDAO.retrieve(requestedUsername);

            if(systemUser != null){
                if(requestedPassword.equals(systemUser.getPassword())){
                    authToken = new AuthToken(requestedUsername, aDAO.randomGenerator());
                    aDAO.insert(authToken);
                    String personID = uDAO.retrieve(requestedUsername).getPersonID();

                    db.closeConnection(true);

                    response = new LoginResponse(authToken.getToken(), requestedUsername, personID);

                }
                else{
                    response = new LoginResponse("Invalid values. Please check your Username and Password");
                    db.closeConnection(true);
                }
            }
            else{
                response = new LoginResponse("Username doesn't exist");
                db.closeConnection(true);
            }
        } catch (DataAccessException e){
            response = new LoginResponse("Couldn't load the data");
            db.closeConnection(true);
        }

        return response;
    }
}
