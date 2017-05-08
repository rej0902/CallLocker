package com.ws.calllocker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ws.calllocker.listener.PatternListener;
import com.ws.calllocker.view.CustomPatternView;

public class MainActivity extends AppCompatActivity implements PatternListener {
    private CustomPatternView mCustomPatternView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAdView();
        mCustomPatternView = (CustomPatternView)findViewById(R.id.custom_pattern);
        Log.e("asd","mCustomPatternView null : "+mCustomPatternView);
        mCustomPatternView.setPatternListener(this);
    }
    private void initAdView(){

        AdView adView = (AdView)findViewById(R.id.adView);
        Log.e("asd","광고 : "+adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("355646072146773").build();
        Log.e("asd","adRequest : "+adRequest.getContentUrl());
        adView.loadAd(adRequest);

    }
    @Override
    public void onAccept() {
        //... notting
    }

    @Override
    public void onReject() {
        //... notting
    }

    @Override
    public void onSavePattern() {
        //패턴 저장했으니 종료한다.
        finish();
    }
}
