package com.ws.calllocker;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ws.calllocker.listener.CloseCallbackListener;
import com.ws.calllocker.receiver.CloseBroadcastReceiver;
import com.ws.calllocker.service.CallLockService;

/**
 * Created by ws on 2017-05-05.
 */

public class EmptyBackgroundActivity extends Activity implements CloseCallbackListener {
    CloseBroadcastReceiver mCloseBroadcastReceiver;
    CallLockWindowManager mCallLockWindowManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove animation
        overridePendingTransition(0, 0);
        initEmptyActivity();

    }

    private void initEmptyActivity() {
        registerCloseRecevier();
        mCallLockWindowManager = new CallLockWindowManager(this);
        mCallLockWindowManager.startLockView();
    }

    private void registerCloseRecevier() {
        mCloseBroadcastReceiver = new CloseBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CallLockCommon.CL_CLOSE_KEY);
        registerReceiver(mCloseBroadcastReceiver, intentFilter);
    }

    private void unregisterCloseRecevier() {
        if (mCloseBroadcastReceiver != null) {
            unregisterReceiver(mCloseBroadcastReceiver);
            mCloseBroadcastReceiver = null;
        }
    }
    private void senddStartCallCommand(){
        Intent intent = new Intent(this, CallLockService.class);
        intent.putExtra(CallLockCommon.CL_COMMAND_KEY,CallLockCommon.CL_START_CALL);
        startService(intent);
    }
    @Override
    public void onClose() {
        // receiver 해제하고
        Log.e("asd","onClose");
        senddStartCallCommand();
        unregisterCloseRecevier();
        finish();
    }

    @Override
    public void finish() {
        mCallLockWindowManager.finish();
        super.finish();
    }
}
