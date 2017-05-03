package com.ws.calllocker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.service.CallLockService;

/**
 * Created by ws on 2017-05-04.
 */

public class InComingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String stateName = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

        int state = 0;
        if(stateName != null) {
            if (stateName.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (stateName.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (stateName.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }
        }

        switch (state){
            case TelephonyManager.CALL_STATE_RINGING:
                startCallLockService(context,number);
                break;
        }

        Log.e("asd","InComingCallReceiver   state : "+state+"  number : "+number+ "  stateStr : " +stateName);

    }

    private void startCallLockService(Context context, String outGoingNumber) {
        Intent intent = new Intent(context, CallLockService.class);
        intent.putExtra(CallLockCommon.CL_COMMAND_KEY, CallLockCommon.CL_RECEIVED_CALL);
        intent.putExtra(CallLockCommon.CL_RECEIVED_CALL_DATA, outGoingNumber);
        context.startService(intent);
    }

}
