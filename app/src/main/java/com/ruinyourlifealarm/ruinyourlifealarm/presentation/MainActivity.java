package com.ruinyourlifealarm.ruinyourlifealarm.presentation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.SmsManager;
import java.util.ArrayList;
import java.util.Calendar;

import android.support.v4.app.ActivityCompat;

import com.ruinyourlifealarm.ruinyourlifealarm.Main;
import com.ruinyourlifealarm.ruinyourlifealarm.R;
import android.content.Intent;

import com.ruinyourlifealarm.ruinyourlifealarm.business.AccessAlarms;
import com.ruinyourlifealarm.ruinyourlifealarm.business.AccessMessages;
import com.ruinyourlifealarm.ruinyourlifealarm.persistence.Alarm;
import com.ruinyourlifealarm.ruinyourlifealarm.persistence.DatabaseHandler;

public class MainActivity extends AppCompatActivity {
    static EditText textBox;
    static EditText editText3;
    Button sendTextButton;
    Button newAlarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sendTextButton = findViewById(R.id.button);
        sendTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSMSButtonClicked();
            }
        });

        newAlarmButton = findViewById(R.id.button2);
        newAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewAlarmButtonClicked();

            }
        });


        textBox = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.SEND_SMS},1);

        DatabaseHandler db = new DatabaseHandler(this);
        Main.setDatabase(db);


        AccessAlarms aa = new AccessAlarms(Main.getDatabase());
        Calendar c = Calendar.getInstance();
        c.set(2017, 11, 3, 11, 30);
        int id = aa.createAlarm("test message", "12345", c, true);

        Alarm a = aa.getAlarm(id);
        Log.d("blah", a.getMessage());
        Log.d("blah2", a.getRecipientPhoneNumber());
//        Log.d("blah3", a.getAlarmTime().toString());
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.YEAR));
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.MONTH));
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.DATE));
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.HOUR));
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.MINUTE));
        Log.d("blah4", a.getAlarmIsOn()+"");

        c.set(2017, 12, 6, 2, 56);
        aa.updateAlarm(id, "test message UPDATED", "1234567", c, false);

        a = aa.getAlarm(id);
        Log.d("blah", a.getMessage());
        Log.d("blah2", a.getRecipientPhoneNumber());
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.YEAR));
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.MONTH));
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.DATE));
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.HOUR));
        Log.d("blah3", ""+a.getAlarmTime().get(Calendar.MINUTE));
        Log.d("blah4", a.getAlarmIsOn()+"");
    }


    private void onSMSButtonClicked(){
        System.out.println(textBox.getText());
        sendLongSMS();
    }

    public void onNewAlarmButtonClicked(){
        Intent intent = new Intent(getApplicationContext(), NewAlarmActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void sendLongSMS() {
        String phoneNumber = editText3.getText().toString();
        String message = textBox.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(message);
        smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
    }
}
