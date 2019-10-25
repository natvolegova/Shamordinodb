package com.example.shamordinodb;

import android.app.Application;
import androidx.room.Room;

import com.example.shamordinodb.Data.DatabaseHelper;
import com.example.shamordinodb.Data.RoomHelperDB;

public class App extends Application {
    private static App instance;
    private DatabaseHelper db;
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        RoomHelperDB helperDB = new RoomHelperDB();
        helperDB.copyDatabase(getApplicationContext(), "shamordinodb.db");

        db = Room.databaseBuilder(getApplicationContext(), DatabaseHelper.class, "shamordinodb.db")
                .allowMainThreadQueries()
                .build();
    }

    public DatabaseHelper getDatabaseInstance() {
        return db;
    }

}
