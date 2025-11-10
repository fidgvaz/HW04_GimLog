package edu.csumb.hw04_gymlog;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import edu.csumb.hw04_gymlog.data.AppDataBase;
import edu.csumb.hw04_gymlog.data.GymLog;
import edu.csumb.hw04_gymlog.data.GymLogDao;

public class DbUnitTest {

    private AppDataBase db;
    private GymLogDao dao;

    @Before
    public void createDb() {
        Context ctx = androidx.test.core.app.ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(ctx, AppDataBase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.gymLogDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertAndRead() {
        dao.deleteAll(); // clean slate
        dao.insert(new GymLog("admin1", "Test set", System.currentTimeMillis()));
        List<GymLog> logs = dao.getAll();
        assertEquals(1, logs.size());
        assertEquals("Test set", logs.get(0).text);
    }
}

