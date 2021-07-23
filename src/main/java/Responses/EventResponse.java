package Responses;

/**
 * An Event Response.
 */
public class EventResponse extends Response{
    /**
     * Username of the user's account this event belongs to.
     */
    private String associatedUsername;
    /**
     * event's unique ID.
     */
    private String eventID;
    /**
     * ID of the person this event belongs to.
     */
    private String personID;
    /**
     * Latitude of the event's location.
     */
    private float latitude;
    /**
     * Longitude of the event's location.
     */
    private float longitude;
    /**
     * Name of country where event occurred.
     */
    private String country;
    /**
     * Name of city where event occurred.
     */
    private String city;
    /**
     * Type of event("birth," baptism," etc.)
     */
    private String eventType;
    /**
     * Year of the event's occurrence.
     */
    private int year;
    /**
     * Event's constructor.
     */
    public EventResponse(){
        super();
    }

    public EventResponse(String message){
        super(message);
    }

    public EventResponse(String eventID, String associatedUsername, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        super();
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
