package Responses;

/**
 * A Fill Response.
 */
public class FillResponse extends Response{
    /**
     * Output if clear succeeds.
     */
    private String message;
    /**
     * Boolean identifier.
     */
    private boolean success;

    /**
     * Constructor for Fill when it has an error.
     * @param message
     */
    FillResponse(String message){
        super(message);
    }
    /**
     * Constructor for when the Fill works.
     */
    FillResponse(){
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
