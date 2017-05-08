package com.ws.calllocker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.ajalt.reprint.core.Reprint;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kyleduo.switchbutton.SwitchButton;

import static com.ws.calllocker.CallLockCommon.CL_PREF_TOGGLE_FINGERPRINT_SETTING_VALUE;
import static com.ws.calllocker.CallLockCommon.CL_PREF_TOGGLE_INCOMING_SETTING_VALUE;
import static com.ws.calllocker.CallLockCommon.CL_PREF_TOGGLE_OUTGOING_SETTING_VALUE;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private SwitchButton mIncomingToggle;
    private SwitchButton mOutgoingToggle;
    private SwitchButton mFingerprintToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAdView();

        mIncomingToggle = (SwitchButton) findViewById(R.id.toggle_incoming);
        mOutgoingToggle = (SwitchButton) findViewById(R.id.toggle_outgoing);
        mFingerprintToggle = (SwitchButton) findViewById(R.id.toggle_fingerprint);

        Log.e("asd","test : "+CallLockCommon.loadBooleanValue(this,CL_PREF_TOGGLE_INCOMING_SETTING_VALUE));

        mIncomingToggle.setChecked(CallLockCommon.loadBooleanValue(this,CL_PREF_TOGGLE_INCOMING_SETTING_VALUE));
        mOutgoingToggle.setChecked(CallLockCommon.loadBooleanValue(this,CL_PREF_TOGGLE_OUTGOING_SETTING_VALUE));
        mFingerprintToggle.setChecked(CallLockCommon.loadBooleanValue(this,CL_PREF_TOGGLE_FINGERPRINT_SETTING_VALUE));

        mIncomingToggle.setOnCheckedChangeListener(this);
        mOutgoingToggle.setOnCheckedChangeListener(this);
        mFingerprintToggle.setOnCheckedChangeListener(this);
    }
    private void initAdView(){

        AdView adView = (AdView)findViewById(R.id.adView);
        Log.e("asd","광고 : "+adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("355646072146773").build();
        Log.e("asd","adRequest : "+adRequest.getContentUrl());
        adView.loadAd(adRequest);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.e("asd","onCheckedChanged : "+isChecked);
        switch (buttonView.getId()) {
            case R.id.toggle_incoming:
                Log.e("asd","toggle_incoming");
                CallLockCommon.setBooleanValue(this,CL_PREF_TOGGLE_INCOMING_SETTING_VALUE,isChecked);
                break;
            case R.id.toggle_outgoing:
                Log.e("asd","toggle_outgoing");
                CallLockCommon.setBooleanValue(this,CL_PREF_TOGGLE_OUTGOING_SETTING_VALUE,isChecked);
                break;
            case R.id.toggle_fingerprint:
                Log.e("asd","toggle_fingerprint");
                Reprint.initialize(this);
                Log.e("asd", "initFingerPrint  Reprint.isHardwarePresent() : " + Reprint.isHardwarePresent() + "  Reprint.hasFingerprintRegistered() : " + Reprint.hasFingerprintRegistered());
                if (Reprint.isHardwarePresent() && Reprint.hasFingerprintRegistered()) {
                    CallLockCommon.setBooleanValue(this,CL_PREF_TOGGLE_FINGERPRINT_SETTING_VALUE,isChecked);
                }else{
                    Toast.makeText(this,"해당 단말은 지문 기능이 없는 단말입니다.",Toast.LENGTH_SHORT).show();
                    buttonView.setChecked(true);
                }
                break;
        }
    }
}
