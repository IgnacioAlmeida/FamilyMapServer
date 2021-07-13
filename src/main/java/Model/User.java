package Model;

import java.util.Objects;

/**
 * A User.
 */
public class User {
    /**
     * Unique user's userName.
     */
    private String userName;
    /**
     * User's password.
     */
    private String password;
    /**
     * User's email.
     */
    private String email;
    /**
     * User's first name.
     */
    private String firstName;
    /**
     * User's last name.
     */
    private String lastName;
    /**
     * User's gender.
     */
    private String gender;
    /**
     * User's unique person ID of the generated Person object.
     */
    private String personID;

    /**
     * Creates a user.
     *
     * @param userName
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     */
    public User(String userName, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
