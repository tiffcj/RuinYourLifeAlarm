package com.ruinyourlifealarm.ruinyourlifealarm.persistence;

import java.util.Calendar;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class Alarm {
    private int id;
    private String message;
    private String recipientPhoneNumber;
    private Calendar alarmTime;

    public Alarm(int id, String message, String recipientPhoneNumber, Calendar alarmTime) {
        this.id = id;
        this.message = message;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.alarmTime = alarmTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public Calendar getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Calendar alarmTime) {
        this.alarmTime = alarmTime;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Message getMessage() {
//        return message;
//    }
//
//    public void setMessage(Message message) {
//        this.message = message;
//    }
//
//    public String getRecipientPhoneNumber() {
//        return recipientPhoneNumber;
//    }
//
//    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
//        this.recipientPhoneNumber = recipientPhoneNumber;
//    }
//
//    public Time getAlarmTime() {
//        return alarmTime;
//    }
//
//    public void setAlarmTime(Time alarmTime) {
//        this.alarmTime = alarmTime;
//    }
//
//    public Alarm(int id, Message message, String recipientPhoneNumber, Time alarmTime) {
//
//        this.id = id;
//        this.message = message;
//        this.recipientPhoneNumber = recipientPhoneNumber;
//        this.alarmTime = alarmTime;
//    }
}
