package com.ws.calllocker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ws on 2017-05-04.
 */

public class CallLockCommon {
    private static final String CL_PREFERENCE_KEY = "CallLocker";


    //preference key
    public static final String CL_PREF_PATTERN_KEY ="CallLockerPatternKey";
    public static final String CL_PREF_UNLOCK_SEESION_KEY ="CallLockerSessionKey";
    // Command Key
    public static final String CL_COMMAND_OUTGOING_SETTING_KEY = "CallLockOutGoingSetting";
    public static final String CL_COMMAND_KEY = "CallLockCommandKey";
    public static final String CL_CLOSE_KEY ="CallLockClose";
    // Command Value
    public static final int CL_FAIL = -1;
    public static final int CL_CLOSE = 1000;
    public static final int CL_REJECT_CALL = 1001;
    public static final int CL_START_CALL =1002;
    public static final int CL_DISCONNECT_CALL = 1004;

    public static final int CL_IN_COMMING_CALL_LOCK =2001;
    public static final int CL_OUT_GOING_CALL_LOCK = 2002;

   // Close BroadCast
    public static final String CL_IN_COMING_CALL_DATA_KEY = "CallLockIncomingCallData";
    public static final String CL_OUT_GOING_CALL_DATA_KEY = "CallLockOutgoingCallData";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(CL_PREFERENCE_KEY, Activity.MODE_PRIVATE);
    }

    /**
     * 발신 잠금 설정 값을 가져옴
     * true : on   false : off
     *
     * @param context
     */
    public static boolean loadBooleanValue(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void setBooleanValue(Context context, String key, boolean result) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putBoolean(key, result).apply();
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
