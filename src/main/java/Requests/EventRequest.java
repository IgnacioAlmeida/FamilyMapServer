package Requests;

public class EventRequest {
    String eventID;

    public EventRequest(String eventID) {
        this.eventID = eventID;
    }

    public EventRequest() {
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}
