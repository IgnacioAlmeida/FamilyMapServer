package Requests;

/**
 * A Fill Request.
 */
public class FillRequest {
    private String username;
    private int generations;

    /**
     * Empty constructor for GSON.
     */
    public FillRequest() {
        this.generations = 4;
        this.username = "";
    }

    public FillRequest(String username, int generations){
        this.generations = generations;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }


}
