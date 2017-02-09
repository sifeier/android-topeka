package com.buke.uikit;

import com.buke.fly.R;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author buke@taobao.com
 * @since 16/10/25
 */
public class AdFloatView extends FloatView {

    private static final String TAG = "AdFloatView";

    private ImageView mIcon;

    private TextView mCountMin, mCountSec;

    private CountDownTimer mCountDownTimer;

    private static final int TOTAL_TIME = 100 * 1000;

    private int times = 10;

    public AdFloatView(Context context) {
        super(context);
    }

    public AdFloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context) {
        super.initView(context);
        mCountMin = (TextView)mView.findViewById(R.id.min);
        mCountSec = (TextView)mView.findViewById(R.id.sec);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ly_float_ad;
    }

    public void doAdRequest() {
        startCountDown();
    }

    private void startCountDown() {

        mCountDownTimer = new CountDownTimer(TOTAL_TIME, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.e(TAG, "onTick, times, millisUntilFinished: " + times + " " + millisUntilFinished);
                if(times == 10) {
                    setVisibility(View.VISIBLE);
                }
                mCountSec.setText(String.format("%02d", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                if(times >0 ) {
                    doAdRequest();
                } else {
                    setVisibility(View.GONE);
                }
            }
        };

        times --;
        mCountDownTimer.start();

    }

    protected boolean isStart = false;

    public boolean isStart() {
        return isStart;
    }

    public void start() {
        doAdRequest();
        isStart = true;
    }

    public void stop() {
        isStart = false;
        if(mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

}
