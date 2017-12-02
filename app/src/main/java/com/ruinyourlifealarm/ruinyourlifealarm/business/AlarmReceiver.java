package com.ruinyourlifealarm.ruinyourlifealarm.business;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;
import android.os.Handler;

import com.ruinyourlifealarm.ruinyourlifealarm.presentation.MainActivity;

public class AlarmReceiver extends BroadcastReceiver
{
    static Ringtone ringtone = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 20s = 20000ms
                if(isRinging()){
                    sendText();
                }
            }
        }, 20000);
    }

    public static void stopRingtone(Context context) {
        if(ringtone != null)
        ringtone.stop();
    }

    public static boolean isRinging(){
        if (ringtone.isPlaying()){
            return true;
        }

        return false;
    }

    public void sendText() {
        MainActivity.sendLongSMS();
    }
}
