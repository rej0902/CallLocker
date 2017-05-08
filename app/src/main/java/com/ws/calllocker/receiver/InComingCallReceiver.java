package com.ws.calllocker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.service.CallLockService;

import static com.ws.calllocker.CallLockCommon.CL_PREF_TOGGLE_INCOMING_SETTING_VALUE;

/**
 * Created by ws on 2017-05-04.
 */

public class InComingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(!CallLockCommon.loadBooleanValue(context,CL_PREF_TOGGLE_INCOMING_SETTING_VALUE)){
            return;
        }
        String stateName = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

        int state = 0;

        if (stateName != null) {
            if (stateName.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (stateName.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (stateName.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }
        }

        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                startCallLockService(context,CallLockCommon.CL_IN_COMMING_CALL_LOCK,number);
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                if(number == null){
                    return;
                }
                // 잠금을 못 풀었을 때, 전화가 끊기면 핀코드 창을 제거해준다.
                if(!CallLockCommon.loadBooleanValue(context,CallLockCommon.CL_PREF_UNLOCK_SEESION_KEY)) {
                    startCallLockService(context, CallLockCommon.CL_DISCONNECT_CALL, number);
                }
                CallLockCommon.setBooleanValue(context,CallLockCommon.CL_PREF_UNLOCK_SEESION_KEY,false);

                break;
        }

        Log.e("asd", "InComingCallReceiver   state : " + state + "  number : " + number + "  stateStr : " + stateName);

    }

    private void startCallLockService(Context context, int command, String outGoingNumber) {
        Intent intent = new Intent(context, CallLockService.class);
        intent.putExtra(CallLockCommon.CL_COMMAND_KEY, command);
        intent.putExtra(CallLockCommon.CL_IN_COMING_CALL_DATA_KEY, outGoingNumber);
        context.startService(intent);
    }

}
