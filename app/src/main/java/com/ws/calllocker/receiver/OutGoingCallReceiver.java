package com.ws.calllocker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.service.CallLockService;

/**
 * Created by ws on 2017-04-02.
 */

public class OutGoingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.e("asd", "onReceive");
        // 발신잠금이 켜져있지 않으면 수행할 필요가 없다.
        if(!CallLockCommon.isOutGoingLockActive(context)){
            return ;
        }

        String command = intent.getAction();

        switch (command) {
            case Intent.ACTION_NEW_OUTGOING_CALL:

                String outGoingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

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
        intent.putExtra(CallLockCommon.CL_COMMAND_KEY, CallLockCommon.CL_REJECT_CALL);
        intent.putExtra(CallLockCommon.CL_REJECT_CALL_DATA, outGoingNumber);
        context.startService(intent);
    }

    //TODO : 다른 단말 테스팅 필요함. 삼성의 경우 5.0 6.0 7.0 동작 확인
    private void disConnectionCall() {
        setResultData(null);
        abortBroadcast();
    }
}
