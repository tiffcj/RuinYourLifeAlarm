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
    private AccessMessages accessMessages;

    public AccessAlarms(DatabaseHandler database) {
        this.database = database;
        this.accessMessages = new AccessMessages(database);
    }

//    public void createAlarm(int messageId, String recipientPhoneNumber, Time alarmTime) {
//        database.createAlarm(messageId, recipientPhoneNumber, alarmTime);
//    }
//
//    public void createAlarm(String customMessage, String recipientPhoneNumber, Time alarmTime) {
//        int messageId = accessMessages.createCustomMessage(customMessage);
//        createAlarm(messageId, recipientPhoneNumber, alarmTime);
//    }

    public int createAlarm(String message, String recipientPhoneNumber, Calendar alarmTime, boolean alarmIsOn) {
        //Inserting into the database returns the id of the just inserted row
        return ((int)database.createAlarm(message, recipientPhoneNumber, alarmTime, alarmIsOn));
    }

    public Alarm getAlarm(int id) {
        return database.getAlarm(id);
    }

    public void updateAlarm(int id, String message, String recipientPhoneNumber, Calendar alarmTime, boolean alarmIsOn) {
        database.updateAlarm(id, message, recipientPhoneNumber, alarmTime, alarmIsOn);
    }
}
