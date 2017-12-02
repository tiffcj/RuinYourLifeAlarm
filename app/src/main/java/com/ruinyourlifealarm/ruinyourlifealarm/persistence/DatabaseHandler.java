package com.ruinyourlifealarm.ruinyourlifealarm.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ruinYourLifeAlarm";

    //Messages table
    private static final String TABLE_MESSAGES = "MESSAGES";
    private static final String KEY_MESSAGES_ID = "ID";
    private static final String KEY_MESSAGES_NAME = "NAME";

    //Alarms table
    private static final String TABLE_ALARMS = "ALARMS";
    private static final String KEY_ALARMS_ID = "ID";
    private static final String KEY_ALARMS_MESSAGE_ID = "MESSAGE ID";
    private static final String KEY_ALARMS_RECIPIENT_PHONE_NUMBER = "RECIPIENT_PHONE_NUMBER";
    private static final String KEY_ALARMS_ALARM_TIME = "ALARM_TIME";

    public DatabaseHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGES_TABLE = "CREATE TABLE MESSAGES (ID INTEGER PRIMARY KEY, NAME TEXT)";
        String CREATE_ALARMS_TABLE = "CREATE TABLE ALARMS (ID INTEGER PRIMARY KEY, ALARM_TIME TIME, MESSAGE_ID INTEGER, RECIPIENT_PHONE_NUMBER TEXT, CONSTRAINT FK_ALARMS_MESSAGES FOREIGN KEY(MESSAGE_ID) REFERENCES MESSAGES(ID))";

        db.execSQL(CREATE_MESSAGES_TABLE);
        db.execSQL(CREATE_ALARMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
        onCreate(db);
    }

    public void createMessage(String messageName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGES_NAME, messageName);

        db.insert(TABLE_MESSAGES, null, values);
        db.close();
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
}
