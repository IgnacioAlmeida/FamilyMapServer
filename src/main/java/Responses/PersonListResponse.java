package Responses;

import Model.Person;

public class PersonListResponse extends Response{
    private Person[] data;

    public PersonListResponse(Person[] data) {
        this.data = data;
    }

    public PersonListResponse() {
        super();
    }

    public PersonListResponse(String message){
        super(message);
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }
}
