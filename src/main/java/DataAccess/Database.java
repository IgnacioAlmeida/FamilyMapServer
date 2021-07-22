package DataAccess;

import java.sql.*;
import java.util.UUID;

public class Database {
    private Connection conn;

    //Whenever we want to make a change to our database we will have to open a connection and use
    //Statements created by that connection to initiate transactions
    public Connection openConnection() throws DataAccessException {
        try {
            //The Structure for this Connection is driver:language:path
            //The path assumes you start in the root of your project unless given a non-relative path
            final String CONNECTION_URL = "jdbc:sqlite:familyMap.sqLite";

            // Open a database connection to the file given in the path
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }

        return conn;
    }

    public Connection getConnection() throws DataAccessException {
        if(conn == null) {
            return openConnection();
        } else {
            return conn;
        }
    }

    //When we are done manipulating the database it is important to close the connection. This will
    //End the transaction and allow us to either commit our changes to the database or rollback any
    //changes that were made before we encountered a potential error.

    //IMPORTANT: IF YOU FAIL TO CLOSE A CONNECTION AND TRY TO REOPEN THE DATABASE THIS WILL CAUSE THE
    //DATABASE TO LOCK. YOUR CODE MUST ALWAYS INCLUDE A CLOSURE OF THE DATABASE NO MATTER WHAT ERRORS
    //OR PROBLEMS YOU ENCOUNTER
    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                //This will commit the changes to the database
                conn.commit();
            } else {
                //If we find out something went wrong, pass a false into closeConnection and this
                //will rollback any changes we made during this connection
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    public void clearTables() throws DataAccessException
    {

        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM events";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM authToken";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM persons";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM users";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }

//    /**
//     * Deletes date from specified table from a username's data
//     * @param username
//     */
    public void clear(String table, String username) throws DataAccessException {
        String sql = "DELETE FROM ? WHERE associatedUsername = ? VALUES(?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, table);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("Error encoutered while deleting persons related to the username");
        }
    }

    /**
     * Generates a random Authorization Token
     * @return
     */
    public String randomGenerator() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
