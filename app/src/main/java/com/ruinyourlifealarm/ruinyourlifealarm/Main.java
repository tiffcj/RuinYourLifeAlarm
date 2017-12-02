package com.ruinyourlifealarm.ruinyourlifealarm;

import com.ruinyourlifealarm.ruinyourlifealarm.persistence.DatabaseHandler;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class Main {
    public static DatabaseHandler database;

    public static void setDatabase(DatabaseHandler db)
    {
        database = db;
    }

    public static DatabaseHandler getDatabase() {
        return database;
    }
}
