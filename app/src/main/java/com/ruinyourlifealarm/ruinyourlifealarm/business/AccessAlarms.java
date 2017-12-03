package com.ruinyourlifealarm.ruinyourlifealarm.business;

import com.ruinyourlifealarm.ruinyourlifealarm.persistence.Alarm;
import com.ruinyourlifealarm.ruinyourlifealarm.persistence.DatabaseHandler;
import com.ruinyourlifealarm.ruinyourlifealarm.persistence.Message;

import java.util.Calendar;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class AccessAlarms {
    private DatabaseHandler database;

    public AccessAlarms(DatabaseHandler database) {
        this.database = database;
    }

//    public void createAlarm(int messageId, String recipientPhoneNumber, Time alarmTime) {
//
//    }
//
//    public void createAlarm(String customMessage, String recipientPhoneNumber, Time alarmTime) {
//        int msgId = (int)(database.createMessage(customMessage));
//        createAlarm(msgId, recipientPhoneNumber, alarmTime);
//    }

    public int createAlarm(String message, String recipientPhoneNumber, Calendar alarmTime, boolean alarmIsOn) {
        return ((int)database.createAlarm(message, recipientPhoneNumber, alarmTime, alarmIsOn));
    }

    public Alarm getAlarm(int id) {
        return database.getAlarm(id);
    }
}
