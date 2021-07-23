package Services;

import DataAccess.*;
import Model.Event;
import Model.Person;
import Requests.PersonRequest;
import Responses.EventResponse;
import Responses.PersonListResponse;
import Responses.PersonResponse;

import java.util.ArrayList;

/**
 * A Person Service.
 */
public class PersonService {

    /**
     * Returns ALL family members of the current user.
     * @param authToken
     * @return
     */
    public PersonListResponse allPersons(String authToken) throws DataAccessException {
        PersonListResponse response = null;
        Database db = new Database();
        ArrayList<Person> persons;

        try{
            PersonDAO pDAO = new PersonDAO(db.getConnection());
            AuthTokenDAO aDAO = new AuthTokenDAO(db.getConnection());

            if(aDAO.retrieve(authToken) != null){
                persons = pDAO.retrievePersons(aDAO.retrieve(authToken).getUserName());
                if(persons != null) {
                    Person[] personsArray = new Person[persons.size()];
                    personsArray = persons.toArray(personsArray);
                    response = new PersonListResponse(personsArray);
                }
                else{
                    response = new PersonListResponse("Error: User doesn't exist");
                }
            }
            else{
                response = new PersonListResponse("Error: Invalid Authorization Token.");
            }
        } catch (DataAccessException e) {
            db.closeConnection(false);
            e.printStackTrace();
        }
        db.closeConnection(true);
        return response;
    }

    /**
     * Returns a family member with the specified ID.
     * @param authToken
     * @param personID
     * @return
     */
    public PersonResponse person(String authToken, String personID) throws DataAccessException {
        PersonResponse response = null;
        Database db = new Database();

        try{
            PersonDAO pDAO = new PersonDAO(db.getConnection());
            AuthTokenDAO aDAO = new AuthTokenDAO(db.getConnection());

            if(aDAO.retrieve(authToken) != null){
                if(pDAO.retrieve(personID) != null){
                    Person person = pDAO.retrieve(personID);
                    if(aDAO.retrieve(authToken).getUserName().equals(person.getAssociatedUsername())){
                        db.closeConnection(true);
                        response = new PersonResponse(person.getPersonID(),person.getAssociatedUsername(),person.getFirstName(),
                                        person.getLastName(),person.getGender(), person.getFatherID(),person.getMotherID(),person.getSpouseID());
                    }
                    else{
                        db.closeConnection(true);
                        response = new PersonResponse("Error: Requested person does not belong to this user.");
                    }
                }
                else{
                    db.closeConnection(true);
                    response = new PersonResponse("Error: Invalid Event ID.");
                }
            }
            else{
                db.closeConnection(true);
                response = new PersonResponse("Error: Invalid Authorization Token.");
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
        }
        return response;
    }
}
