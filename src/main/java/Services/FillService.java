package Services;

import DataAccess.*;
import AncestorData.FileParser;
import AncestorData.Location;
import AncestorData.Locations;
import AncestorData.Names;
import Model.Event;
import Model.Person;
import Model.User;
import Requests.FillRequest;
import Responses.Response;
import Serializer.JsonSerializer;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

/**
 * A Fill Service.
 */
public class FillService {
    final private int LOCATIONS_COUNT = 978;
    final private int FEMALE_NAMES_COUNT = 147;
    final private int MALE_NAMES_COUNT = 144;
    final private int LAST_NAMES_COUNT = 152;
    private Random random = new Random();
    private int peopleCounter = 0;
    private int eventsCounter = 0;
    Person currentPerson;
    int requestedGenerations;


    //Get locations from file
    String allLocations = FileParser.startParse("json/locations.json").toString();
    Locations locations = JsonSerializer.deserialize(allLocations, Locations.class);

    //Get female names from file
    String femaleNames = FileParser.startParse("json/fnames.json").toString();
    Names fnames = JsonSerializer.deserialize(femaleNames, Names.class);

    //Get male names from file
    String maleNames = FileParser.startParse("json/mnames.json").toString();
    Names mnames = JsonSerializer.deserialize(maleNames, Names.class);

    //Get last names from file
    String lastnames = FileParser.startParse("json/snames.json").toString();
    Names snames = JsonSerializer.deserialize(lastnames, Names.class);

    public FillService() throws IOException {
    }

    /**
     * Populates the server's database with generated data for the specified user name.
     * @param fillRequest
     * @return response
     */
    public Response fill(FillRequest fillRequest) throws DataAccessException {
        Response response = null;
        Database db = new Database();
        String requestedUsername = fillRequest.getUsername();
        requestedGenerations = fillRequest.getGenerations();


        try{
            if(requestedGenerations > 0){
                UserDAO uDAO = new UserDAO(db.getConnection());
                PersonDAO pDAO = new PersonDAO(db.getConnection());
                EventDAO eDAO = new EventDAO(db.getConnection());

                User user = uDAO.retrieve(requestedUsername);

                pDAO.clear(requestedUsername);
                eDAO.clear(requestedUsername);

                //Creates the user's person and birth event
                currentPerson = new Person(user.getPersonID(),user.getUserName(),user.getFirstName(),user.getLastName(),
                                                    user.getGender(),null,null,null);
                Location birthLocation = locations.locationAt(random.nextInt(LOCATIONS_COUNT));
                Event userBirth = new Event(eDAO.randomGenerator(),user.getUserName(),user.getPersonID(), birthLocation.getLatitude(),
                                            birthLocation.getLongitude(), birthLocation.getCountry(), birthLocation.getCity(),
                                    "Birth", 1995);
                pDAO.insert(currentPerson);
                peopleCounter += 1;

                eDAO.insert(userBirth);
                eventsCounter += 1;

                //Creates user's ancestors profiles and events
                createAncestors(db.getConnection(), currentPerson,1,1995);

                db.closeConnection(true);

                String message = "Successfully added " + peopleCounter + " persons and " + eventsCounter +
                        " events to the database.";

                response = new Response();
                response.setMessage(message);

            }
            else{
                response = new Response("Error: The number of generations must be a non-negative integer.");
                db.closeConnection(true);
            }
        } catch(DataAccessException e){
            response = new Response("Error: Couldn't load the data");
            db.closeConnection(true);
        }
        return response;

    }
    private void createAncestors(Connection conn,Person child, int generation, int birthYear) throws DataAccessException {
        PersonDAO pDAO = new PersonDAO(conn);

        String fatherID = pDAO.randomGenerator();
        String motherID = pDAO.randomGenerator();

        Person father = new Person(fatherID, currentPerson.getAssociatedUsername(),mnames.nameAt(random.nextInt(MALE_NAMES_COUNT)),
                snames.nameAt(random.nextInt(LAST_NAMES_COUNT)), "m",null,null,null);
        Person mother = new Person(motherID,currentPerson.getAssociatedUsername(),fnames.nameAt(random.nextInt(FEMALE_NAMES_COUNT)),
                father.getLastName(), "f",null,null,father.getPersonID());
        mother.setSpouseID(father.getFatherID());

        father.setSpouseID(motherID);
        mother.setSpouseID(fatherID);

        pDAO.insert(father);
        pDAO.insert(mother);
        peopleCounter += 2;


        PersonDAO childDAO = new PersonDAO(conn);
        childDAO.retrieve(child.getPersonID());
        childDAO.updateParentsID(fatherID, motherID, child.getPersonID());

        int[] currentBirthYears = createEvents(conn, child, father, mother, birthYear);

        if(generation < requestedGenerations){

            createAncestors(conn, father, generation + 1, currentBirthYears[1]);
            createAncestors(conn, mother, generation + 1, currentBirthYears[0]);
        }


    }

    private int[] createEvents(Connection conn, Person child, Person father, Person mother, int birthYear) throws DataAccessException {
        EventDAO eDAO = new EventDAO(conn);

        Location momBirthLocation = locations.locationAt(random.nextInt(LOCATIONS_COUNT));
        Location dadBirthLocation = locations.locationAt(random.nextInt(LOCATIONS_COUNT));

        int motherBirthYear = birthYear - 23;
        int fatherBirthYear = birthYear - 25;
        int[] birthYears = new int[2];
        birthYears[0] = motherBirthYear;
        birthYears[1] = fatherBirthYear;

        Event motherBirth = new Event(eDAO.randomGenerator(), currentPerson.getAssociatedUsername(),mother.getPersonID(),
                momBirthLocation.getLatitude(), momBirthLocation.getLongitude(), momBirthLocation.getCountry(), momBirthLocation.getCity(),
                "Birth", motherBirthYear);
        Event dadBirth = new Event(eDAO.randomGenerator(), currentPerson.getAssociatedUsername(),father.getPersonID(),
                dadBirthLocation.getLatitude(), dadBirthLocation.getLongitude(), dadBirthLocation.getCountry(), dadBirthLocation.getCity(),
                "Birth", fatherBirthYear);
        eDAO.insert(motherBirth);
        eDAO.insert(dadBirth);

        Location marriageLocaiton = locations.locationAt(random.nextInt(LOCATIONS_COUNT));
        Event motherMarriage = new Event(eDAO.randomGenerator(), currentPerson.getAssociatedUsername(), mother.getPersonID(),
                marriageLocaiton.getLatitude(), marriageLocaiton.getLongitude(), marriageLocaiton.getCountry(), marriageLocaiton.getCity(),
                "Marriage", birthYear + 22);
        Event dadMarriage = new Event(eDAO.randomGenerator(), currentPerson.getAssociatedUsername(), father.getPersonID(),
                marriageLocaiton.getLatitude(), marriageLocaiton.getLongitude(), marriageLocaiton.getCountry(), marriageLocaiton.getCity(),
                "Marriage", birthYear + 22);
        eDAO.insert(motherMarriage);
        eDAO.insert(dadMarriage);

        Location momDeathLocation = locations.locationAt(random.nextInt(LOCATIONS_COUNT));
        Location dadDeathLocation = locations.locationAt(random.nextInt(LOCATIONS_COUNT));
        Event motherDeath = new Event(eDAO.randomGenerator(), currentPerson.getAssociatedUsername(), mother.getPersonID(),
                momDeathLocation.getLatitude(), momDeathLocation.getLongitude(), momDeathLocation.getCountry(),
                momDeathLocation.getCity(), "Death", birthYear + 60);
        Event dadDeath = new Event(eDAO.randomGenerator(), currentPerson.getAssociatedUsername(), father.getPersonID(),
                dadDeathLocation.getLatitude(), dadDeathLocation.getLongitude(), dadDeathLocation.getCountry(),
                dadDeathLocation.getCity(), "Death", birthYear + 60);
        eDAO.insert(motherDeath);
        eDAO.insert(dadDeath);
        eventsCounter += 6;
        return birthYears;
    }



}
