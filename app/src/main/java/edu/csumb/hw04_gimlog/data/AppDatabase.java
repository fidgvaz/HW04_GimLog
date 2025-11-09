package edu.csumb.hw04_gimlog.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GymLog.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract GymLogDao gymLogDao();

    public static AppDatabase getInstance(Context ctx) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    ctx.getApplicationContext(),
                                    AppDatabase.class,
                                    "gymLog.db"
                            )
                            .allowMainThreadQueries() // OK for homework/simplicity
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

