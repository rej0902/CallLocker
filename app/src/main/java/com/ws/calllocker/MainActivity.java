package com.ws.calllocker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ws.calllocker.listener.PatternListener;
import com.ws.calllocker.view.CustomPatternView;

public class MainActivity extends AppCompatActivity implements PatternListener {
    private CustomPatternView mCustomPatternView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomPatternView = (CustomPatternView)findViewById(R.id.custom_pattern);
        Log.e("asd","mCustomPatternView null : "+mCustomPatternView);
        mCustomPatternView.setPatternListener(this);

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
