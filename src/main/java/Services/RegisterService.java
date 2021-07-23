package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.User;
import Requests.FillRequest;
import Requests.RegisterRequest;
import Responses.RegisterResponse;
import Responses.Response;

import java.io.IOException;

/**
 * A Register Service.
 */
public class RegisterService {

    /**
     * Creates a new user account and generates 4 generations of ancestor data for the new user.
     * @param registerRequest
     * @return response
     */
    public RegisterResponse register(RegisterRequest registerRequest) throws DataAccessException {
        RegisterResponse response = null;
        Database db = new Database();
        String requestedUsername = registerRequest.getUserName();
        String requestedPassword = registerRequest.getPassword();
        String requestedEmail = registerRequest.getEmail();
        String requestedFirstName = registerRequest.getFirstName();
        String requestedLastName = registerRequest.getLastName();
        String requestedGender = registerRequest.getGender();
        AuthToken authtoken = null;

        try{
            AuthTokenDAO aDAO = new AuthTokenDAO(db.getConnection());
            UserDAO uDAO = new UserDAO(db.getConnection());
            PersonDAO pDAO = new PersonDAO(db.getConnection());
            EventDAO eDAO = new EventDAO(db.getConnection());
            String personID = pDAO.randomGenerator();

            if(uDAO.retrieve(registerRequest.getUserName()) != null){
                response = new RegisterResponse("Error: User already exists, please choose a new one.");
                db.closeConnection(true);
                return response;
            }

            authtoken = new AuthToken(requestedUsername,aDAO.randomGenerator());
            aDAO.insert(authtoken);

            User user = new User(requestedUsername, requestedPassword,requestedEmail,requestedFirstName,requestedLastName,
                                 requestedGender,personID);

            uDAO.insert(user);

            db.closeConnection(true);

            //Generates ancestors data
            FillService fillService = new FillService();
            FillRequest fillRequest = new FillRequest(registerRequest.getUserName(),4);
            fillService.fill(fillRequest);

            response = new RegisterResponse(authtoken.getToken(), requestedUsername,personID);


        } catch (DataAccessException | IOException e) {
            response = new RegisterResponse("Error: Couldn't load the data");
            e.printStackTrace();
            db.closeConnection(false);
        }
        return response;
    }
}
