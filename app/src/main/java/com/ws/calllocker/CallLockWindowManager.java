package com.ws.calllocker;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.WindowManager;

import com.ws.calllocker.view.LockView;

/**
 * Created by ws on 2017-05-05.
 */

public class CallLockWindowManager {
    private Context mContext = null;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    private LockView mLockView;

    public CallLockWindowManager(Context context) {
        mContext = context;
        initWindowManager();
    }

    private void initWindowManager() {
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT);


    }

    public void startLockView() {
        mLockView = new LockView(mContext);
        mWindowManager.addView(mLockView.getView(),mLayoutParams);
    }

    public void finish(){
        Log.e("asd","windowManager finish");
        try{
            mWindowManager.removeView(mLockView.getView());
            mLockView = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
