package DataAccess;

import Model.Event;
import Model.Person;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAO {
    private final Connection conn;

    public PersonDAO(Connection conn){this.conn = conn;}

    /**
     * Inserts a new Person to data
     * @param person
     */
    public void insert(Person person) throws DataAccessException{
    String sql = "INSERT INTO persons (personID, associatedUsername, firstName, lastName, " +
            "gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, person.getPersonID());
        stmt.setString(2, person.getAssociatedUsername());
        stmt.setString(3, person.getFirstName());
        stmt.setString(4, person.getLastName());
        stmt.setString(5, person.getGender());
        stmt.setString(6, person.getFatherID());
        stmt.setString(7, person.getMotherID());
        stmt.setString(8, person.getSpouseID());

        stmt.executeUpdate();
    } catch (SQLException e){
        throw new DataAccessException("Error encountered while inserting into the database");
    }

    }

    /**
     * Deletes a given Person from the data
     * @param personID
     */
    public void delete(String personID){

    }

    /**
     * Gets a Person from the data
     * @param personID
     * @return person
     */
    public Person retrieve(String personID) throws DataAccessException, SQLException{
        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM persons WHERE personID = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if(rs.next()){
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Gets all the person related to the user
     * @param user
     * @return persons
     */
    public Person[] getAll(String user){
        ArrayList<Person> persons = new ArrayList<>();
        return null;
    }

    /**
     * Deletes all the people from the table
     */
    public void clear() throws DataAccessException{
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM persons";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}
