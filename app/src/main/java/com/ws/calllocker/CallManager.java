package com.ws.calllocker;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by ws on 2017-05-04.
 */

public class CallManager {
    private Context mContext;
    private TelephonyManager mTelephonyManager;
    private PhoneStateListener mPhoneStateListener;
    private String mPrevCallNumber;

    public CallManager(Context context) {
        mContext = context;
        initPhoneStateListener();
    }

    private void initPhoneStateListener() {
        mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.e("asd", "IDLE" + "  number : " + incomingNumber);
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Log.e("asd", "RINGING" + "  number : " + incomingNumber);
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Log.e("asd", "OFFHOOK" + "  number : " + incomingNumber);
                        break;

                }
            }
        };
        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }


    private void removePhoneStateListener() {
        if (mTelephonyManager != null) {
            mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
        }
    }
}
