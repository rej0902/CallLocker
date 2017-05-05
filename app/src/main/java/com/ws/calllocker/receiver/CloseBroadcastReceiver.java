package com.ws.calllocker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.listener.CloseCallbackListener;

/**
 * Created by ws on 2017-05-05.
 */

public class CloseBroadcastReceiver extends BroadcastReceiver {
    CloseCallbackListener mListener;
    public CloseBroadcastReceiver(CloseCallbackListener listener){
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Log.e("asd","CloseBroadcastReceiver action : "+action);
        switch (action){
            case CallLockCommon.CL_CLOSE_KEY:
                mListener.onClose();
                break;
        }
    }
}
