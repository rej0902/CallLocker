package com.ws.calllocker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import com.ws.calllocker.listener.CloseCallbackListener;
import com.ws.calllocker.service.CallLockService;

/**
 * Created by ws on 2017-05-05.
 */

public class EmptyBackgroundActivity extends Activity implements CloseCallbackListener {
    private CallLockWindowManager mCallLockWindowManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("asd", "empty onCreate");
        super.onCreate(savedInstanceState);
        //remove animation
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        Log.e("asd", "있냐 : " + mCallLockWindowManager);
        overridePendingTransition(0, 0);
        initEmptyActivity();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("asd", "empty onNewIntent");
        boolean isFinish = intent.getBooleanExtra(CallLockCommon.CL_CLOSE_KEY, false);
        if (isFinish) {
            finish();
        }

    }

    @Override
    protected void onPause() {
        Log.e("asd", "empty onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.e("asd", "empty onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.e("asd", "empty onDestroy");

        super.onDestroy();
    }

    private void initEmptyActivity() {
        if (mCallLockWindowManager == null) {
            mCallLockWindowManager = new CallLockWindowManager(this, this);
            mCallLockWindowManager.startLockView();
        }
    }

    private void sendStartCallCommand() {
        // unlock session true
        CallLockCommon.setBooleanValue(this, CallLockCommon.CL_PREF_UNLOCK_SEESION_KEY, true);
        Intent intent = new Intent(this, CallLockService.class);
        intent.putExtra(CallLockCommon.CL_COMMAND_KEY, CallLockCommon.CL_START_CALL);
        startService(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("asd", "onBackPressed");
    }

    @Override
    public void onClose() {
        // receiver 해제하고
        Log.e("asd", "onClose");
        sendStartCallCommand();
        finish();
    }

    @Override
    public void finish() {
        mCallLockWindowManager.finish();
        super.finish();
    }
}
