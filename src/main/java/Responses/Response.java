package Responses;

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
     * Constructor for when we have an error.
     * @param message
     */
    Response(String message){
        this.message = message;
        success = false;
    }

    /**
     * Constructor for when everything works fine.
     */
    Response(){
        success = true;
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
