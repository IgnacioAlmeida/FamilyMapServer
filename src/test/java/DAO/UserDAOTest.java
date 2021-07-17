package DAO;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Database db;
    private User bestUser;
    private UserDAO uDao;
   // private User secondaryUser;

    @BeforeEach
    public void setUp() throws DataAccessException{
        //here we can set up any classes or variables we will need for the rest of our tests
        db = new Database();
        bestUser = new User("ignacioh", "123456","ignacio.rdea@gmail.com","Ignacio",
                "R. de Almeida", "m","123ignacio");
        //Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        db.clearTables();
        //Pass that connection to the EventDAO so it can access the database
        uDao = new UserDAO(conn);

//        secondaryUser = new User("ignacioh2", "123456","ignacio.rdea@gmail.com","Ignacio",
//                "R. de Almeida", "m","123ignacio");
//        uDao.insert(secondaryUser);
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insert(bestUser);
        User compareTest = uDao.retrieve(bestUser.getUserName());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException{
        uDao.insert(bestUser);
        assertThrows(DataAccessException.class, ()-> uDao.insert(bestUser));
    }

    @Test
    public void retrievePass() throws  DataAccessException{
        uDao.insert(bestUser);
        User compareTest = uDao.retrieve(bestUser.getUserName());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void retrieveFail() throws  DataAccessException{
        User compareTest = uDao.retrieve(bestUser.getUserName());
        assertNull(compareTest);
    }

    @Test
    public void clearPass(){
        assertDoesNotThrow(()-> uDao.clear());
    }
}
