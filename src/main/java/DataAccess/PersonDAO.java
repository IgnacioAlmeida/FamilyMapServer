package DataAccess;

import Model.Event;
import Model.Person;

import java.util.ArrayList;

public class PersonDAO {
    /**
     * Inserts a new Person to data
     * @param person
     */
    public void insert(Person person){

    }

    /**
     * Deletes a given Person from the data
     * @param personID
     */
    public void delete(String personID){

    }

    /**
     * Gets a Person from the data
     * @param personID
     * @return person
     */
    public Person get(String personID){

        Person person = null;
        return person;
    }

    /**
     * Gets all the person related to the user
     * @param user
     * @return persons
     */
    public Person[] getAll(String user){
        ArrayList<Person> persons = new ArrayList<>();
        return null;
    }

    /**
     * Deletes all the people from the table
     */
    public void clear(){

    }
}
