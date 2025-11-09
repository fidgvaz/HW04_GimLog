package edu.csumb.hw04_gimlog.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gym_logs")
public class GymLog {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;   // e.g., "admin1"
    public String text;       // workout note, e.g., "Bench 3x10"
    public long timestamp;    // System.currentTimeMillis()

    public GymLog(String username, String text, long timestamp) {
        this.username = username;
        this.text = text;
        this.timestamp = timestamp;
    }
}
