package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDAO;
import Model.Event;
import Responses.EventResponse;
import Responses.EventsListResponse;

import java.util.ArrayList;

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

            if(aDAO.retrieve(authToken) != null){
                if(eDAO.retrieve(eventID) != null){
                    Event event = eDAO.retrieve(eventID);
                    if(aDAO.retrieve(authToken).getUserName().equals(event.getAssociatedUsername())){
                        db.closeConnection(true);
                        response = new EventResponse(event.getEventID(),event.getAssociatedUsername(),event.getPersonID(),
                                event.getLatitude(),event.getLongitude(),event.getCountry(),event.getCity(),event.getEventType(),
                                event.getYear());
                    }
                    else{
                        db.closeConnection(true);
                        response = new EventResponse("Requested event does not belong to this user.");
                    }
                }
                else{
                    db.closeConnection(true);
                    response = new EventResponse("Invalid Event ID.");
                }
            }
            else{
                db.closeConnection(true);
                response = new EventResponse("Invalid Authorization Token.");
            }

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
    public EventsListResponse allEvents(String authToken) throws DataAccessException {
        EventsListResponse response = null;
        Database db = new Database();
        ArrayList<Event> events;

        try{
            EventDAO eDAO = new EventDAO(db.getConnection());
            AuthTokenDAO aDAO = new AuthTokenDAO(db.getConnection());

            if(aDAO.retrieve(authToken) != null){
                events = eDAO.retrieveUserEvents(aDAO.retrieve(authToken).getUserName());
                Event[] eventsArray = new Event[events.size()];
                eventsArray = events.toArray(eventsArray);
                response = new EventsListResponse(eventsArray);
            }
            else{
                response = new EventsListResponse("Invalid Authorization Token.");
                db.closeConnection(true);
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
        }

        db.closeConnection(true);

        return response;
    }

}
