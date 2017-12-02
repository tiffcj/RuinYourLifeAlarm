package com.ruinyourlifealarm.ruinyourlifealarm.business;

import com.ruinyourlifealarm.ruinyourlifealarm.persistence.Alarm;
import com.ruinyourlifealarm.ruinyourlifealarm.persistence.DatabaseHandler;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class AccessAlarms {
    private DatabaseHandler database;

    public AccessAlarms(DatabaseHandler database) {
        this.database = database;
    }

    public void createAlarm() {
        //TODO
    }

    public Alarm getAlarm() {
        //TODO
        return null;
    }
}
