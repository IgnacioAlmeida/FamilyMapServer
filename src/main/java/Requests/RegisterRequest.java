package Requests;

/**
 * Class used for a Register Request body
 */
public class RegisterRequest {
    /**
     * The user's Username.
     */
    private String username;
    /**
     * The Username's password.
     */
    private String password;
    /**
     * The user's email.
     */
    private String email;
    /**
     * The user's first name.
     */
    private String firstName;
    /**
     * The user's last name.
     */
    private String lastName;
    /**
     * The user's gender. "M," or "F."
     */
    private String gender;

    /**
     * Empty constructor for GSON
     */
    public RegisterRequest() {
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
