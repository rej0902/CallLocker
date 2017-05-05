package com.ws.calllocker;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ws.calllocker.service.CallLockService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent startServiceIntent = new Intent(getApplicationContext(), CallLockService.class);
        startService(startServiceIntent);

        //Hellow WS
        //write  permission enabled?


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
