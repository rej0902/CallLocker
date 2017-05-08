package com.ws.calllocker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.service.CallLockService;

import static com.ws.calllocker.CallLockCommon.CL_PREF_TOGGLE_OUTGOING_SETTING_VALUE;

/**
 * Created by ws on 2017-04-02.
 */

public class OutGoingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.e("asd", "onReceive");
        // 발신잠금이 켜져있지 않으면 수행할 필요가 없다.
        if(!CallLockCommon.loadBooleanValue(context,CL_PREF_TOGGLE_OUTGOING_SETTING_VALUE)){
            return;
        }

        String command = intent.getAction();

        switch (command) {
            case Intent.ACTION_NEW_OUTGOING_CALL:
                // 잠금을 풀었을 경우에는 보면안됨
                if(CallLockCommon.loadBooleanValue(context,CallLockCommon.CL_PREF_UNLOCK_SEESION_KEY)){
                    return;
                }
                String outGoingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                Log.e("asd", "OutGoingCallReceiver  ACTION_NEW_OUTGOING_CALL  outGoingNumber : "+outGoingNumber);
                // 만약 발신 정보를 가져오지 못했더라면 실행하지 않는다.
                if (outGoingNumber == null || outGoingNumber.equals("")) {
                    break;
                }
                disConnectionCall();
                startCallLockService(context, outGoingNumber);

                break;

            default:
                //...
                break;
        }
    }


    private void startCallLockService(Context context, String outGoingNumber) {
        Intent intent = new Intent(context, CallLockService.class);
        intent.putExtra(CallLockCommon.CL_COMMAND_KEY, CallLockCommon.CL_OUT_GOING_CALL_LOCK);
        intent.putExtra(CallLockCommon.CL_OUT_GOING_CALL_DATA_KEY, outGoingNumber);
        context.startService(intent);
    }

    //TODO : 다른 단말 테스팅 필요함. 삼성의 경우 5.0 6.0 7.0 동작 확인
    private void disConnectionCall() {
        setResultData(null);
        abortBroadcast();
    }
}
