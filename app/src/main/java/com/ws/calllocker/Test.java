package com.ws.calllocker;

import android.util.Log;

import com.ws.calllocker.listener.CloseCallbackListener;

/**
 * Created by ws on 2017-04-09.
 */

public class Test {
    CloseCallbackListener mListener;
    public Test(){

    }

    public void setOnClickListener(CloseCallbackListener listener){
        mListener = listener;;
    }

    private void killService(){
        mListener.onClose();
    }

    public void reviveService(){
        Log.e("Log","Revival Service Message");
    }
}
