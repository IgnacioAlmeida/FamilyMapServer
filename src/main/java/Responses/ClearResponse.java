package Responses;

/**
 * A Clear Response.
 */
public class ClearResponse extends Response{
    /**
     * Output if clear succeeds.
     */
    private String message;
    /**
     * Boolean identifier.
     */
    private boolean success;

    /**
     * Constructor for Clear when it has an error.
     * @param message
     */
    ClearResponse(String message){
        super(message);
    }
    /**
     * Constructor for when the clear works.
     */
    ClearResponse(){
        super();
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
