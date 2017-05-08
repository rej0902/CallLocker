package com.ws.calllocker;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eftimoff.patternview.PatternView;
import com.ws.calllocker.listener.PatternListener;
import com.ws.calllocker.view.CustomPatternView;

import static com.ws.calllocker.CallLockCommon.CL_PREF_PATTERN_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragmentPatternView extends Fragment implements PatternListener {
    private CustomPatternView mPatternView;
    private View mView;
    public IntroFragmentPatternView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_intro_fragment_pattern_view, container, false);
        initView();
        return mView;
    }

    private void initView() {
        mPatternView = (CustomPatternView) mView.findViewById(R.id.custom_pattern);
        mPatternView.setPatternListener(this);
    }

    @Override
    public void onAccept() {

    }

    @Override
    public void onReject() {

    }

    @Override
    public void onSavePattern() {
        //...패턴 저장했을 때,
        Log.e("asd","onSavePattern");
        // 여기서 넘기는 부분으로
    }
}
