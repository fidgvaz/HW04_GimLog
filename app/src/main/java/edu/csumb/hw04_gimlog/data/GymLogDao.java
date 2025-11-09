package edu.csumb.hw04_gimlog.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GymLogDao {

    @Query("SELECT * FROM gym_logs WHERE username = :u ORDER BY timestamp DESC")
    List<GymLog> getAllForUser(String u);

    @Insert
    long insert(GymLog log);

    @Update
    int update(GymLog log);

    @Delete
    int delete(GymLog log);

    @Query("DELETE FROM gym_logs")
    int deleteAll();
}
