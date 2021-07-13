package Responses;

import Model.Person;

/**
 * A Person Response.
 */
public class PersonResponse extends Response{
    /**
     * Name of user account this person belongs to.
     */
    private String associatedUsername;
    /**
     * Person's unique ID.
     */
    private String personID;
    /**
     * Person's first name.
     */
    private String firstName;
    /**
     * Person's last name.
     */
    private String lastName;
    /**
     * Person's gender. Either "m" or "f."
     */
    private String gender;
    /**
     * ID of person's gather (Optional).
     */
    private String fatherID;
    /**
     * ID of person's mother (Optional).
     */
    private String motherID;
    /**
     * ID of person's spouse (Optional).
     */
    private String spouseID;
    /**
     * Boolean identifier.
     */
    private boolean success;
    /**
     * All family members of the current user.
     */
    private Person[] people;
    /**
     * PersonResponse constructor.
     */
    PersonResponse(){
        super();
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Person[] getPeople() {
        return people;
    }

    public void setPeople(Person[] people) {
        this.people = people;
    }
}
