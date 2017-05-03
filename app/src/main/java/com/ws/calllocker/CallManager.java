package com.ws.calllocker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

    public void startCall(String number){
        Log.e("asd","start Call  : "+number);
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("tel:"+number));
        //permission check
        try {
            mContext.startActivity(intent);

        }catch (Exception e){
            Log.e("asd","error : "+e.getMessage());
            e.printStackTrace();
        }
    }

    private void initPhoneStateListener() {
        mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        mPhoneStateListener = new PhoneStateListener() {
//            @Override
//            public void onCallStateChanged(int state, String incomingNumber) {
//                switch (state) {
//                    case TelephonyManager.CALL_STATE_IDLE:
//                        Log.e("asd", "IDLE" + "  number : " + incomingNumber);
//                        break;
//                    case TelephonyManager.CALL_STATE_RINGING:
//                        Log.e("asd", "RINGING" + "  number : " + incomingNumber);
//                        break;
//                    case TelephonyManager.CALL_STATE_OFFHOOK:
//                        Log.e("asd", "OFFHOOK" + "  number : " + incomingNumber);
//                        break;
//
//                }
//            }
//        };
//        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }


    private void removePhoneStateListener() {
        if (mTelephonyManager != null) {
            mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
        }
    }
}
