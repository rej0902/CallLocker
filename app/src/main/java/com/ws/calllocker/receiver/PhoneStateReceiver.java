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
        Log.e("asd", "onReceive");
        //
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.e("asd", "state : " + state);

        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        telManager.listen(new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {

                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.e("asd", "IDLE"+"  number : "+incomingNumber);
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Log.e("asd", "RINGING"+"  number : "+incomingNumber);
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        incomingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                        Log.e("asd", "OFFHOOK"+"  number : "+incomingNumber);
                        break;

                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);

        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);


            // TODO : reject outgoing call TESTS
            setResultData(null);
            abortBroadcast();
            Log.e("asd", "out : "+number);
        }
    }

}
