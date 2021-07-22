package DataAccess;

import Model.AuthToken;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.UUID;

public class AuthTokenDAO extends Database{
    private final Connection conn;

    public AuthTokenDAO(Connection conn){this.conn = conn;}

    /**
     * Creates a new Authorization Token to data
     * @param authToken
     */
    public void insert(AuthToken authToken) throws DataAccessException {
        String sql = "INSERT INTO authToken (username, token) VALUES(?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, authToken.getUserName());
            stmt.setString(2, authToken.getToken());

            stmt.executeUpdate();
        } catch(SQLException e){
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }



    /**
     * Gets an Authorization Token from the data with a given userID
     * @param username
     * @return user
     */
    public AuthToken retrieve(String username) throws DataAccessException {
        AuthToken authToken;
        ResultSet rs = null;
        String sql = "SELECT * FROM authToken WHERE username = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                authToken = new AuthToken(rs.getString("username"), rs.getString("token"));
                return authToken;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Deletes all the Authorization Tokens from the table
     */
    public void clear() throws DataAccessException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM authToken";
            stmt.executeUpdate(sql);
        } catch (SQLException e){
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }

    /**
     * Gets back the given Authorization Token
     * @param authToken
     * @return newAuthToken
     */
//    public AuthToken retrieveAuthToken(String authToken) throws DataAccessException {
//        AuthToken token;
//        ResultSet rs  = null;
//        String sql = "SELECT * FROM authToken WHERE token = ?;";
//        try(PreparedStatement stmt = conn.prepareStatement(sql)){
//            stmt.setString(1, authToken);
//            rs = stmt.executeQuery();
//            if(rs.next()){
//                token = new token(rs.getString("username"), rs.getString("token"));
//                return token;
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//            throw new DataAccessException("Error encountered while finding event");
//        } finally {
//            if(rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }

    /**
     * Deletes an Authorization Token from the data
     * @param authToken
     */
    public void delete(String authToken) throws DataAccessException {
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM authToken WHERE token = ?");
            stmt.setString(1,authToken);
            stmt.executeUpdate();
            //stmt.close();//TODO not sure about this one
        } catch (SQLException e){
            throw new DataAccessException("SQL Error encountered deleting an authorization token");
        }
    }


}
