package DAO;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.PersonDAO;
import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private Person bestPerson;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException{
        db = new Database();
        bestPerson = new Person("123ignacio", "ignacioh", "ignacio", "R. de Almeida",
                "m", "32roberto", "32sandra","31anais");

        Connection conn = db.getConnection();
        db.clearTables();
        pDao = new PersonDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {//retrieve before insert and that should be null
        pDao.insert(bestPerson);
        Person compareTest = pDao.retrieve(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void insertFail() throws  DataAccessException {
        pDao.insert(bestPerson);
        assertThrows(DataAccessException.class, ()-> pDao.insert(bestPerson));
    }

    @Test
    public void retrievePass() throws DataAccessException {
        pDao.insert(bestPerson);
        Person compareTest = pDao.retrieve(bestPerson.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestPerson, compareTest);
    }


    @Test
    public void retrieveFail() throws  DataAccessException {
        Person compareTest = pDao.retrieve(bestPerson.getPersonID());
        assertNull(compareTest);
    }

    @Test
    public void clearPass() {
        assertDoesNotThrow(()-> pDao.clear());
    }
}
