package com.ws.calllocker;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ws.calllocker.service.CallLockService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent startServiceIntent = new Intent(getApplicationContext(), CallLockService.class);
        startService(startServiceIntent);
    }

    public void mStart_normal(View v) {
        startService(new Intent(getApplicationContext(), com.ws.calllocker.service.Service_WindowManager_Normal.class));
    }
    public void mStop_normal(View v) {
        stopService(new Intent(getApplicationContext(), com.ws.calllocker.service.Service_WindowManager_Normal.class));
    }
    public void mStart_forced(View v) {
        startService(new Intent(getApplicationContext(), com.ws.calllocker.service.Service_WindowManager_Forced.class));
    }
    public void mStop_forced(View v) {
        stopService(new Intent(getApplicationContext(), com.ws.calllocker.service.Service_WindowManager_Forced.class));
    }

    private void permissionCheck(){
        String permission[] = new String[]{
          Manifest.permission.READ_PHONE_STATE};
        ArrayList<String> notGrantedPermission = new ArrayList<>();
//        for(String p : permission){
//            if(!PermissionUtils.h)
//        }
        //추가
    }

}
