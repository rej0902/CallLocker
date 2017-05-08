package com.ws.calllocker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ws.calllocker.listener.CloseCallbackListener;
import com.ws.calllocker.listener.PatternListener;
import com.ws.calllocker.view.CustomPatternView;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragmentPatternView extends Fragment implements PatternListener {
    private CustomPatternView mPatternView;
    private View mView;
    private CloseCallbackListener mListener;

    public IntroFragmentPatternView() {
        // Required empty public constructor

    }

    public void setCloseListener(CloseCallbackListener listener) {
        mListener = listener;
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
        Log.e("asd", "onSavePattern");
        mListener.onClose();
    }
}
