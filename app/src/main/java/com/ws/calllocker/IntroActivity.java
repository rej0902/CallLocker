package com.ws.calllocker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.ws.calllocker.listener.CloseCallbackListener;

import static com.ws.calllocker.CallLockCommon.CL_PREF_PATTERN_KEY;

public class IntroActivity extends AppIntro implements CloseCallbackListener {
    public final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CallLockCommon.loadBooleanValue(getApplicationContext(), CallLockCommon.CL_PREF_NOT_FIRST_START)) {
            startMainActivity();
            startActivity(new Intent(this, SplashActivity.class));
            return;
        }
        startActivity(new Intent(this, SplashActivity.class));
        addSlide(AppIntroFragment.newInstance("CallLocker!", "CallLocker는 여러분의 통화생활을 존중합니다.", R.drawable.ic_lock, Color.parseColor("#1976D2")));
        addSlide(AppIntroFragment.newInstance("Permission Required", "CallLocker는 통화 상태(전화를 걸거나 받기)를 확인할 수 있는 권한이 요구됩니다.", R.drawable.ic_skip_white, Color.parseColor("#1976D2")));
        addSlide(AppIntroFragment.newInstance("Processing...", "권한을 요구하는 창이 열리면, 수락을 눌러주세요.", R.drawable.ic_skip_white, Color.parseColor("#1976D2")));
        addSlide(AppIntroFragment.newInstance("감사합니다.", "권한을 얻었습니다.", R.drawable.ic_skip_white, Color.parseColor("#1976D2")));
        IntroFragmentPatternView introFragmentPatternView = new IntroFragmentPatternView();
        introFragmentPatternView.setCloseListener(this);
        addSlide(introFragmentPatternView);
        addSlide(AppIntroFragment.newInstance("Enjoy your life!", "환경설정이 완료되었습니다.", R.drawable.ic_skip_white, Color.parseColor("#1976D2")));
        //setBarColor(Color.parseColor("#FF51B5"));
        //setSeparatorColor(Color.parseColor("#000000"));
        //askForPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 5);
        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);

        setSlideOverAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        CallLockCommon.setBooleanValue(getApplicationContext(), CallLockCommon.CL_PREF_NOT_FIRST_START, true);
        startMainActivity();
        // Do something when users tap on Done button.
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //허가시
                    pager.setCurrentItem(3);
                } else {
                    //거부시
                    pager.setCurrentItem(1);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        int position = pager.getCurrentItem();

        switch (position){
            case 1:
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                }else{
                    pager.setCurrentItem(4);
                }
                break;
            case 3:
                break;
            case 4:
                setSwipeLock(true);
                String loadPattern = CallLockCommon.loadValue(getApplicationContext(), CL_PREF_PATTERN_KEY);
                if (!loadPattern.equals("")) {
                    pager.setCurrentItem(5);
                }
                break;
            case 5:
                break;
        }

        super.onSlideChanged(oldFragment, newFragment);
        Log.e("asd", "onSlideChanged");
        // Do something when the slide changes.
    }


    // 패턴설정이 끝나고 나면 오는 callback
    @Override
    public void onClose() {
        pager.setCurrentItem(5);
    }
}
