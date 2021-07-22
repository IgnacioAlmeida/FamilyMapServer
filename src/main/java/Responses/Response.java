package Responses;

import DataAccess.DataAccessException;
import DataAccess.Database;

/**
 * A Parent class for the Responses.
 */
public class Response {
    /**
     * Message printed when we have an error.
     */
    protected String message;
    /**
     * Boolean identifier
     */
    protected boolean success;
    /**
     * Constructor for when everything works fine.
     */
    public Response(){
        success = true;
    }

    public Response(String message){
        this.success = false;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
