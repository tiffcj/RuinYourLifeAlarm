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

    //Messages table table/column names
    private static final String TABLE_MESSAGES = "MESSAGES";
    private static final String KEY_MESSAGES_ID = "ID";
    private static final String KEY_MESSAGES_NAME = "NAME";

    //Alarms table table/column names
    private static final String TABLE_ALARMS = "ALARMS";
    private static final String KEY_ALARMS_ID = "ID";
    private static final String KEY_ALARMS_MESSAGE_ID = "MESSAGE_ID";
    private static final String KEY_ALARMS_RECIPIENT_PHONE_NUMBER = "RECIPIENT_PHONE_NUMBER";
    private static final String KEY_ALARMS_ALARM_TIME = "ALARM_TIME";

    public DatabaseHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES + " (" + KEY_MESSAGES_ID + " INTEGER PRIMARY KEY, " + KEY_MESSAGES_NAME + " TEXT)";
        String CREATE_ALARMS_TABLE = "CREATE TABLE " + TABLE_ALARMS + " (" + KEY_ALARMS_ID + " INTEGER PRIMARY KEY, " + KEY_ALARMS_ALARM_TIME + " TIME, " + KEY_ALARMS_MESSAGE_ID + " INTEGER, " + KEY_ALARMS_RECIPIENT_PHONE_NUMBER + " TEXT, " + "CONSTRAINT FK_" + TABLE_ALARMS + "_" + TABLE_MESSAGES + " FOREIGN KEY(" + KEY_ALARMS_MESSAGE_ID + ") REFERENCES " + TABLE_MESSAGES + "(" + KEY_MESSAGES_ID + "))";

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

    public void createMessage(String msg) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGES_NAME, msg);

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
