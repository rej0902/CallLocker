package com.ws.calllocker.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eftimoff.patternview.PatternView;
import com.ws.calllocker.CallLockCommon;
import com.ws.calllocker.R;
import com.ws.calllocker.listener.PatternListener;

import static com.ws.calllocker.CallLockCommon.CL_PREF_PATTERN_KEY;

/**
 * Created by ws on 2017-05-06.
 */

public class CustomPatternView extends LinearLayout implements PatternView.OnPatternDetectedListener, PatternView.OnPatternCellAddedListener, PatternView.OnPatternClearedListener, PatternView.OnPatternStartListener {
    private View mView;
    private Context mContext;
    private PatternView mPatternView;
    private TextView mResultTextView;
    private int mTotalAddCount = 0;
    private PatternListener mListener;

    public CustomPatternView(Context context) {
        super(context);
        initView(context);
    }

    public CustomPatternView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public CustomPatternView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setPatternListener(PatternListener listener) {
        mListener = listener;
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.pattern_view, this);
        mPatternView = (PatternView) mView.findViewById(R.id.patternView);
        mResultTextView = (TextView) mView.findViewById(R.id.resultTextView);
        mPatternView.setOnPatternDetectedListener(this);
        mPatternView.setOnPatternCellAddedListener(this);
        mPatternView.setOnPatternClearedListener(this);
        mPatternView.setOnPatternStartListener(this);

    }


    @Override
    public void onPatternDetected() {
        Log.e("asd", "onPatternDetected");
        String inputPattern = mPatternView.getPatternString();
        if (mTotalAddCount < 4) {
            mResultTextView.setText("4개 이상의 점을 연걸하세요.");
        } else {
            String loadPattern = CallLockCommon.loadValue(mContext, CL_PREF_PATTERN_KEY);
            Log.e("asd", "loadPattern : " + loadPattern + "  inputPattern : " + inputPattern + "  같냐 : " + loadPattern.equals(inputPattern));
            if (loadPattern.equals("")) {
                CallLockCommon.saveValue(mContext, CL_PREF_PATTERN_KEY, inputPattern);
                mListener.onSavePattern();
            } else if (loadPattern.equals(inputPattern)) {
                mResultTextView.setText(" ");
                mListener.onAccept();
            } else {
                mResultTextView.setText("패턴이 틀립니다.");
                mListener.onReject();
            }
        }
        mPatternView.clearPattern(100);
    }

    @Override
    public void onPatternCellAdded() {
        mTotalAddCount++;
        Log.e("asd", "onPatternCellAdded");
    }


    @Override
    public void onPatternCleared() {
        mTotalAddCount = 0;
        Log.e("asd", "onPatternCleared");

    }

    @Override
    public void onPatternStart() {
        Log.e("asd", "onPatternStart");
    }
}
