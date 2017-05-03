package com.ws.calllocker.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.CallManager;
import com.ws.calllocker.receiver.OutGoingCallReceiver;

/**
 * Created by ws on 2017-04-02.
 */

public class CallLockService extends Service {
    OutGoingCallReceiver mCallStateRecevier;
    WindowManager mWindowManager;
    CallManager mCallManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mCallManager = new CallManager(getApplicationContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int command = intent.getIntExtra(CallLockCommon.CL_COMMAND_KEY,CallLockCommon.CL_FAIL);

        if(command == CallLockCommon.CL_FAIL){
            return START_STICKY;
        }

        switch (command){
            case CallLockCommon.CL_REJECT_CALL:

                break;

        }

        return super.onStartCommand(intent, flags, startId);
    }


}
