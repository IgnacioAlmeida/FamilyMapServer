package Services;

import DataAccess.*;
import Model.Event;
import Model.Person;
import Model.User;
import Requests.LoadRequest;
import Responses.Response;
import Serializer.JsonSerializer;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * A Load Service.
 */
public class LoadService {

    /**
     * Clears all data from the database (just like the /clear API), and then loads the posted user,
     * person, and event into the database.
     * @param request
     * @return response
     */
    public Response load(LoadRequest request) throws DataAccessException {
        Response response = null;
        Database db = new Database();

        try{
            UserDAO uDao = new UserDAO(db.getConnection());
            PersonDAO pDao = new PersonDAO(db.getConnection());
            EventDAO eDao = new EventDAO(db.getConnection());
            db.clearTables();

            ArrayList<User> users = request.getUsers();
            ArrayList<Person> persons = request.getPersons();
            ArrayList<Event> events = request.getEvents();

            //Inserts data from the request to the database
            for(int i = 0; i < users.size(); i++){
                uDao.insert(users.get(i));
            }

            for(int i = 0; i < persons.size(); i++){
                pDao.insert(persons.get(i));
            }


            for(int i = 0; i < events.size(); i++){
                eDao.insert(events.get(i));
            }

            db.closeConnection(true);

            String message = "Successfully added " + users.size() + " users, " + persons.size() +
                             " persons, and " + events.size() + " events to the database.\n";
            response = new Response();
            response.setMessage(message);

        } catch (DataAccessException e){
            String failResponse = "Error: Couldn\'t load data";
            response = new Response(failResponse);
            db.closeConnection(true);
        }

        return response;
    }

}
