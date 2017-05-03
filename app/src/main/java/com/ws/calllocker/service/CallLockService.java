package com.ws.calllocker.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.ws.calllocker.receiver.PhoneStateReceiver;

/**
 * Created by ws on 2017-04-02.
 */

public class CallLockService extends Service {
    PhoneStateReceiver mCallStateRecevier;
    WindowManager mWindowManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }
}
