package DAO;

import DataAccess.AuthTokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import Model.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
    private Database db;
    private AuthToken bestToken;
    private AuthTokenDAO tDao;

    @BeforeEach
    public void setUp() throws DataAccessException{
        db = new Database();
        bestToken = new AuthToken("ignacio","123");
        Connection conn = db.getConnection();
        db.clearTables();
        tDao = new AuthTokenDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException{
        tDao.insert(bestToken);
        AuthToken compareTest = tDao.retrieve(bestToken.getToken());
        assertNotNull(compareTest);
        assertEquals(bestToken,compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException{
        tDao.insert(bestToken);
        assertThrows(DataAccessException.class, ()-> tDao.insert(bestToken));
    }

    @Test
    public void retrievePass() throws DataAccessException{
        tDao.insert(bestToken);
        AuthToken compareTest = tDao.retrieve(bestToken.getToken());
        assertNotNull(compareTest);
        assertEquals(bestToken,compareTest);
    }

    @Test
    public void retrieveFail() throws DataAccessException{
        AuthToken compareToken = tDao.retrieve(bestToken.getUserName());
        assertNull(compareToken);
    }

    @Test
    public void clearPass() throws DataAccessException{
        assertDoesNotThrow(()->tDao.clear());
    }



}
