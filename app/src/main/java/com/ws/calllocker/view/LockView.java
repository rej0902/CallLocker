package com.ws.calllocker.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.ajalt.reprint.core.AuthenticationFailureReason;
import com.github.ajalt.reprint.core.AuthenticationListener;
import com.github.ajalt.reprint.core.Reprint;
import com.ws.calllocker.R;
import com.ws.calllocker.listener.CloseCallbackListener;
import com.ws.calllocker.listener.PatternListener;

/**
 * Created by ws on 2017-05-04.
 */

public class LockView implements PatternListener, AuthenticationListener {
    private View mView;
    private Context mContext;
    private CustomPatternView mCustomPatternView;
    private CloseCallbackListener mCloseCallbackListener;
    public LockView(Context context,CloseCallbackListener closeCallbackListener) {
        mContext = context;
        mCloseCallbackListener = closeCallbackListener;
        LayoutInflater LayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = LayoutInflater.inflate(R.layout.view_lockscreen, null);
        mCustomPatternView = (CustomPatternView) mView.findViewById(R.id.custom_pattern);
        mCustomPatternView.setPatternListener(this);

        initFingerPrint();
    }
    private void initFingerPrint(){
        Reprint.initialize(mContext);
        Log.e("asd","initFingerPrint  Reprint.isHardwarePresent() : "+Reprint.isHardwarePresent()+"  Reprint.hasFingerprintRegistered() : "+Reprint.hasFingerprintRegistered());
        if(Reprint.isHardwarePresent() && Reprint.hasFingerprintRegistered()){
            Reprint.authenticate(this);
        }
    }

    public View getView() {
        return mView;
    }


    @Override
    public void onAccept() {
        Log.e("asd","lock view : "+"onAccept");
        mCloseCallbackListener.onClose();
    }

    @Override
    public void onReject() {

    }

    @Override
    public void onSavePattern() {

    }

//  finger print
    @Override
    public void onSuccess(int moduleTag) {
        Reprint.cancelAuthentication();
        mCloseCallbackListener.onClose();
    }

    @Override
    public void onFailure(AuthenticationFailureReason failureReason, boolean fatal, CharSequence errorMessage, int moduleTag, int errorCode) {
        mCustomPatternView.setResultText("다시 시도해주세요.");
    }
}
