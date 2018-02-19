package com.ruinyourlifealarm.ruinyourlifealarm.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ruinYourLifeAlarm";

    //Messages table table/column names
    private static final String TABLE_MESSAGES = "MESSAGES";
    private static final String KEY_MESSAGES_ID = "ID";
    private static final String KEY_MESSAGES_NAME = "NAME";

    //Alarms table table/column names
    private static final String TABLE_ALARMS = "ALARMS";
    private static final String KEY_ALARMS_ID = "ID";
//    private static final String KEY_ALARMS_MESSAGE_ID = "MESSAGE_ID";
    private static final String KEY_ALARMS_MESSAGE_ID = "MESSAGE";
    private static final String KEY_ALARMS_RECIPIENT_PHONE_NUMBER = "RECIPIENT_PHONE_NUMBER";
    private static final String KEY_ALARMS_ALARM_TIME = "ALARM_TIME";
    private static final String KEY_ALARMS_ALARM_IS_ON = "ALARM_IS_ON";

    public DatabaseHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES + " (" + KEY_MESSAGES_ID + " INTEGER PRIMARY KEY, " + KEY_MESSAGES_NAME + " TEXT)";
//        String CREATE_ALARMS_TABLE = "CREATE TABLE " + TABLE_ALARMS + " (" + KEY_ALARMS_ID + " INTEGER PRIMARY KEY, " + KEY_ALARMS_ALARM_TIME + " TIME, " + KEY_ALARMS_MESSAGE_ID + " INTEGER, " + KEY_ALARMS_RECIPIENT_PHONE_NUMBER + " TEXT, " + "CONSTRAINT FK_" + TABLE_ALARMS + "_" + TABLE_MESSAGES + " FOREIGN KEY(" + KEY_ALARMS_MESSAGE_ID + ") REFERENCES " + TABLE_MESSAGES + "(" + KEY_MESSAGES_ID + "))";
        String CREATE_ALARMS_TABLE = "CREATE TABLE ALARMS (ID INTEGER PRIMARY KEY, MESSAGE TEXT, RECIPIENT_PHONE_NUMBER TEXT, ALARM_TIME TEXT, ALARM_IS_ON INTEGER)";

        db.execSQL(CREATE_MESSAGES_TABLE);
        db.execSQL(CREATE_ALARMS_TABLE);

        //Inserting default messages to start
        db.execSQL(getInsertMessageCmd(1, "I just had 6 tequila shots"));
        db.execSQL(getInsertMessageCmd(2, "I paid for WinRAR"));
        db.execSQL(getInsertMessageCmd(3, "I kick puppies in my spare time"));
        db.execSQL(getInsertMessageCmd(4, "I just pirated 7 movies"));
    }

    private String getInsertMessageCmd(int id, String msg) {
        return "INSERT INTO " + TABLE_MESSAGES + " VALUES(" + id + ", '" + msg + "')";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
        onCreate(db);
    }

    //Messages

    public int createMessage(String msg) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGES_NAME, msg);

        long id = db.insert(TABLE_MESSAGES, null, values);
        db.close();

        return (int)id;
    }

    public Message getMessage(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MESSAGES, new String[] { KEY_MESSAGES_ID,
                        KEY_MESSAGES_NAME }, KEY_MESSAGES_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Message msg = new Message(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));

        return msg;
    }

    public List<Message> getAllMessages() {
        List<Message> messageList = new ArrayList<Message>();
        String selectQuery = "SELECT * FROM " + TABLE_MESSAGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                Message msg = new Message(id, name);
                messageList.add(msg);
            } while (cursor.moveToNext());
        }

        return messageList;
    }


    //Alarms

//    public long createAlarm(int messageId, String recipientPhoneNumber, Time alarmTime) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_ALARMS_MESSAGE_ID, messageId);
//        values.put(KEY_ALARMS_RECIPIENT_PHONE_NUMBER, recipientPhoneNumber);
//        values.put(KEY_ALARMS_ALARM_TIME, alarmTime.toString());
//
//        long id = db.insert(TABLE_ALARMS, null, values);
//        db.close();
//
//        return id;
//    }

    public long createAlarm(String message, String recipientPhoneNumber, Calendar alarmTime, Boolean alarmIsOn) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ALARMS_MESSAGE_ID, message);
        values.put(KEY_ALARMS_RECIPIENT_PHONE_NUMBER, recipientPhoneNumber);
        values.put(KEY_ALARMS_ALARM_TIME, convertCalendarToString(alarmTime));

        if (alarmIsOn) {
            values.put(KEY_ALARMS_ALARM_IS_ON, 1);
        } else {
            values.put(KEY_ALARMS_ALARM_IS_ON, 0);
        }

        long id = db.insert(TABLE_ALARMS, null, values);
        db.close();

        return id;
    }

    public void updateAlarm(int id, String message, String recipientPhoneNumber, Calendar alarmTime, Boolean alarmIsOn) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ALARMS_MESSAGE_ID, message);
        values.put(KEY_ALARMS_RECIPIENT_PHONE_NUMBER, recipientPhoneNumber);
        values.put(KEY_ALARMS_ALARM_TIME, convertCalendarToString(alarmTime));

        if (alarmIsOn) {
            values.put(KEY_ALARMS_ALARM_IS_ON, 1);
        } else {
            values.put(KEY_ALARMS_ALARM_IS_ON, 0);
        }

        db.update(TABLE_ALARMS, values, KEY_ALARMS_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Alarm getAlarm(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ALARMS, new String[] { KEY_ALARMS_ID,
                        KEY_ALARMS_MESSAGE_ID, KEY_ALARMS_RECIPIENT_PHONE_NUMBER, KEY_ALARMS_ALARM_TIME, KEY_ALARMS_ALARM_IS_ON }, KEY_ALARMS_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Calendar c = convertStringToCalendar(cursor.getString(3));
        boolean bool = Integer.parseInt(cursor.getString(4)) == 1;
        Alarm alarm = new Alarm(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), c, bool);

        return alarm;
    }

    private String convertCalendarToString(Calendar c) {
        return c.getTimeInMillis()+"";
    }

    private Calendar convertStringToCalendar(String s) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(s));

        return c;
    }

    //TODO once we have >1 alarm
    public List<Alarm> getAllAlarms() {
        return null;
    }
}
