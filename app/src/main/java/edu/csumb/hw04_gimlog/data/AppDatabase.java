package edu.csumb.hw04_gimlog.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {GymLog.class},
        version = 1,
        exportSchema = true
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GymLogDao gymLogDao();
}
