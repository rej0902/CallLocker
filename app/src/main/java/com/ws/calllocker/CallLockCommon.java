package com.ws.calllocker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ws on 2017-05-04.
 */

public class CallLockCommon {
    private static final String CL_PREFERENCE_KEY = "CallLocker";
    // Command Key
    public static final String CL_COMMAND_OUTGOING_SETTING = "CallLockOutGoingSetting";
    public static final String CL_COMMAND_KEY = "CallLockCommandKey";

    // Command Value
    public static final int CL_FAIL = -1;
    public static final int CL_REJECT_CALL = 1001;
    public static final int CL_RECEIVED_CALL =1002;

    public static final String CL_REJECT_CALL_DATA = "CallLockRejectCallData";
    public static final String CL_RECEIVED_CALL_DATA = "CallLockReceivedCallData";
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(CL_PREFERENCE_KEY, Activity.MODE_PRIVATE);
    }

    /**
     * 발신 잠금 설정 값을 가져옴
     * true : on   false : off
     *
     * @param context
     */
    public static boolean isOutGoingLockActive(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getBoolean(CallLockCommon.CL_COMMAND_OUTGOING_SETTING, false);
    }

    public static void setOutGoingLockActive(Context context, boolean result) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putBoolean(CallLockCommon.CL_COMMAND_OUTGOING_SETTING, result).apply();
    }


    public static void saveValue(Context context, String key, String value) {
        if (context == null || key == null || value == null) {
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static String loadValue(Context context, String key) {
        if (context == null || key == null) {
            return "";
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

}
