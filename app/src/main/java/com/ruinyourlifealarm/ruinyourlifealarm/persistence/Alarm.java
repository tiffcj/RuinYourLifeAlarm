package com.ruinyourlifealarm.ruinyourlifealarm.persistence;

import java.sql.Time;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class Alarm {
    private int id;
    private Message message;
    private String recipientPhoneNumber;
    private Time alarmTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public Time getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Time alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Alarm(int id, Message message, String recipientPhoneNumber, Time alarmTime) {

        this.id = id;
        this.message = message;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.alarmTime = alarmTime;
    }
}
