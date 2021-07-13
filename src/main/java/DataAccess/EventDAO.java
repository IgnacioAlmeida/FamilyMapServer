package DataAccess;

import Model.Event;

import java.util.ArrayList;

public class EventDAO {
    /**
     * Inserts a new Event to data
     * @param event
     */
    public void insert(Event event){

    }

    /**
     * Deletes a given Event from the data
     * @param personID
     */
    public void delete(String personID){

    }

    /**
     * Gets the events in a person's life
     * @param personID
     * @return events
     */
    public Event getFromPerson(String personID){
        ArrayList<Event> events = new ArrayList<>();
        return null;
    }

    /**
     * Gets the events in the user's life
     * @param userID
     * @return events
     */
    public Event[] getFromUser(String userID){
        ArrayList<Event> events = new ArrayList<>();
        return null;
    }

    /**
     * Gets an Event from the Data
     * @param eventID
     * @return event
     */
    public Event get(String eventID){
        Event event = null;
        return event;
    }


    /**
     * Deletes all the events from the table
     */
    public void clear(){

    }
}
