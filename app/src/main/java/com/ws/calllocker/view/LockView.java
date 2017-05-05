package com.ws.calllocker.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.R;
import com.ws.calllocker.listener.PatternListener;

/**
 * Created by ws on 2017-05-04.
 */

public class LockView implements PatternListener {
    private View mView;
    private Context mContext;
    private CustomPatternView mCustomPatternView;

    public LockView(Context context) {
        mContext = context;
        LayoutInflater LayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = LayoutInflater.inflate(R.layout.view_lockscreen, null);
        mCustomPatternView = (CustomPatternView) mView.findViewById(R.id.custom_pattern);
        mCustomPatternView.setPatternListener(this);
    }


    public View getView() {
        return mView;
    }


    @Override
    public void onAccept() {
        Log.e("asd","lock view : "+"onAccept");
        Intent intent = new Intent();
        intent.setAction(CallLockCommon.CL_CLOSE_KEY);
        mContext.sendBroadcast(intent);
    }

    @Override
    public void onReject() {

    }

    @Override
    public void onSavePattern() {

    }
}
