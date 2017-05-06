package com.ws.calllocker.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.CallManager;
import com.ws.calllocker.EmptyBackgroundActivity;

import static com.ws.calllocker.CallLockCommon.CL_CLOSE_KEY;

/**
 * Created by ws on 2017-04-02.
 */

public class CallLockService extends Service {
    private CallManager mCallManager;
    private boolean mIsStartCall = false;
    private String mStartCallNumber = "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCallManager = new CallManager(getApplicationContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return START_STICKY;
        }
        int command = intent.getIntExtra(CallLockCommon.CL_COMMAND_KEY, CallLockCommon.CL_FAIL);

        if (command == CallLockCommon.CL_FAIL) {
            return START_STICKY;
        }

        switch (command) {
            case CallLockCommon.CL_OUT_GOING_CALL_LOCK:
                String getOutgoingNumber = intent.getStringExtra(CallLockCommon.CL_OUT_GOING_CALL_DATA_KEY);
                if (getOutgoingNumber != null) {
                    startBackgroundActivity(false);
                    mIsStartCall = true;
                    mStartCallNumber = getOutgoingNumber;
                }
                break;

            case CallLockCommon.CL_IN_COMMING_CALL_LOCK:
                startBackgroundActivity(false);
                mIsStartCall = false;
                break;

            case CallLockCommon.CL_START_CALL:
                if (mIsStartCall) {
                    mCallManager.startCall(mStartCallNumber);
                }
                break;

            case CallLockCommon.CL_DISCONNECT_CALL:
                startBackgroundActivity(true);
                stopSelf();
                break;

            case CallLockCommon.CL_CLOSE:
                break;

            default:
                //...
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }


    private void startBackgroundActivity(boolean isFinish) {
        Log.e("asd", "startBackgroundActivity");
        Intent backgroundIntent = new Intent(this, EmptyBackgroundActivity.class);
        if (isFinish) {
            backgroundIntent.putExtra(CL_CLOSE_KEY, true);
        }
        backgroundIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        backgroundIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(backgroundIntent);
    }

}
