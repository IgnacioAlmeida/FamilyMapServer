package DataAccess;

import Model.Event;

import java.sql.*;
import java.util.ArrayList;

public class EventDAO extends Database{
    private final Connection conn;

    public EventDAO(Connection conn)
    {
        this.conn = conn;
    }
    /**
     * Inserts a new Event to data
     * @param event
     */
    public void insert(Event event) throws DataAccessException{

        String sql = "INSERT INTO events (eventID, associatedUsername, personID, latitude, longitude, " +
                "country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Deletes a given Event from the data //TODO it may by deleted by user and not eventID
     * @param eventID
     */
    public void delete(String eventID){
        String sql =  "DELETE FROM events WHERE personID = eventID+";
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
    public Event retrieve(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs = null;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public ArrayList<Event> retrieveUserEvents(String username) throws DataAccessException {
        ArrayList<Event> events = new ArrayList<>();
        Event event;
        ResultSet rs = null;
        String sql = "SELECT * FROM Events WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    /**
     * Deletes all the events from the table
     */
    public void clear() throws DataAccessException{
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Events";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
    /**
     * Deletes all persons from a username's data
     * @param username
     */
    public void clear(String username) throws DataAccessException {
        String sql = "DELETE FROM events WHERE associatedUsername = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("Error encountered while deleting persons related to the username");
        }
    }

//    public int getBirthYear(String personID) throws DataAccessException {
//        String sql = "SELECT year FROM events WHERE personID = ? and eventType = 'Birth';";
//        int year = 0;
//        try(PreparedStatement stmt = conn.prepareStatement(sql)){
//            stmt.setString(1,personID);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                year = rs.getInt(year);
//            }
//            return year;
//        } catch (SQLException e){
//            throw new DataAccessException("Error encountered while selecting event");
//        }
//    }
}
