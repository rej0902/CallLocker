package com.ws.calllocker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by ws on 2017-04-02.
 */

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.e("asd","onReceive");
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.e("asd","state : "+state);

        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        telManager.listen(new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {
                if (state == TelephonyManager.CALL_STATE_IDLE) {
                    Log.e("asd", "IDLE");
                } else if (state == TelephonyManager.CALL_STATE_RINGING) {
                    Log.e("asd", "RINGING");
                } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    Log.e("asd", "OFFHOOK");
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);

        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            Log.e("asd", "out");
        }
    }

}
