package Responses;

/**
 * A Load Response.
 */
public class LoadResponse extends Response{
    /**
     * Output if clear succeeds.
     */
    private String message;
    /**
     * Boolean identifier.
     */
    private boolean success;

    /**
     * Constructor for Load when it has an error.
     * @param message
     */
    LoadResponse(String message){
        super(message);
    }
    /**
     * Constructor for when the Load works.
     */
    LoadResponse(){
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
