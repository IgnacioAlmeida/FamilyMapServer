package Responses;

import Model.Event;

public class EventsListResponse extends Response{

    private Event[] data;

    public EventsListResponse(Event[] data){
        this.data = data;
    }

    public EventsListResponse(){super();}

    public EventsListResponse(String message){super();}

    public Event[] getEvents() {
        return data;


    }

    public void setEvents(Event[] data) {
        this.data = data;
    }
}
