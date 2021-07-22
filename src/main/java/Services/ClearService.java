package Services;

import DataAccess.DataAccessException;
import DataAccess.Database;
import Responses.Response;

/**
 * A Clear Service.
 */
public class ClearService {

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and generated
     * person and event data.
     * @return response
     */
    public Response clear() throws DataAccessException {
        Database db = new Database();
        Response response = null;


        try{
            db.openConnection();
            db.clearTables();
            db.closeConnection(true);
            response = new Response();
            response.setMessage("Clear succeeded.");
        } catch (DataAccessException e){
            String failResponse = "Data couldn't be cleared";
            response = new Response(failResponse);
            db.closeConnection(true);
        }

        return response;
    }
}
