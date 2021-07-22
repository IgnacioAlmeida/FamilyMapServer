package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDAO;
import Requests.EventRequest;
import Responses.EventResponse;

/**
 * An Event Service.
 */
public class EventService {

    /**
     * Returns an event from a person's life with the specified ID.
     * @param authToken
     * @param eventID
     * @return
     * @throws DataAccessException
     */
    public EventResponse event(String authToken, String eventID) throws DataAccessException {
        EventResponse response = null;
        Database db = new Database();

        try{
            EventDAO eDAO = new EventDAO(db.getConnection());
            AuthTokenDAO aDAO = new AuthTokenDAO(db.getConnection());

            if(aDAO.)

        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
        }
        return response;
    }

    /**
     * Returns all events for all family members of the current user.
     * @param authToken
     * @return
     * @throws DataAccessException
     */
    public EventResponse allEvent(String authToken) throws DataAccessException {
        EventResponse response = null;
        Database db = new Database();

        try{
            EventDAO eDAO = new EventDAO(db.getConnection());
            AuthTokenDAO aDAO = new AuthTokenDAO(db.getConnection());


        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
        }

        return response;
    }

}
