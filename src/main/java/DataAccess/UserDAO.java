package DataAccess;

import Model.User;

import javax.xml.crypto.Data;
import java.sql.*;

public class UserDAO {
    private final Connection conn;

    public UserDAO(Connection conn){this.conn = conn;}
    /**
     * Inserts a new user to data
     * @param user
     */
    public void insert(User user) throws DataAccessException {
        String sql = "INSERT INTO users (username, password, email, firstName, lastName, gender, personID) " +
                "VALUES(?,?,?,?,?,?,?);";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }



    /**
     * Gets a user from the data
     * @param username
     * @return user
     */
    public User retrieve(String username) throws DataAccessException {
        User user;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE username = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while retrieving user");
        } finally {
          if(rs != null){
              try{
                  rs.close();
              } catch (SQLException e){
                  e.printStackTrace();
              }
          }
        }
        return null;
    }

    /**
     * Deletes all the usernames from the table
     */
    public void clear() throws DataAccessException{
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM users;";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}
