package com.buke;

import com.google.samples.apps.topeka.R;

import com.buke.uikit.AdFloatView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author buke@taobao.com
 * @since 16/10/24
 */
public class FloatViewActivity extends Activity {

    private static final String TAG = "FloatViewUI";

    private GestureDetector mGestureDetector;

    private int screenWidth, screenHeight;

    View.OnTouchListener mOnTouchListener;

    private float startX = 0;

    private float startY = 0;

    private boolean isMove;

    ImageView avatar1, avatar2, avatar3;

    AdFloatView mAdFloatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ac_float_view);

        avatar1 = (ImageView)findViewById(R.id.iv_1);
        avatar2 = (ImageView)findViewById(R.id.iv_2);
        avatar3 = (ImageView)findViewById(R.id.iv_3);

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatViewActivity.this, "avatar1 show!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(FloatViewActivity.this, DesignMeActivity.class));

            }
        });

        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatViewActivity.this, "avatar2 show!", Toast.LENGTH_LONG).show();
            }
        });

        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatViewActivity.this, "avatar3 show!", Toast.LENGTH_LONG).show();
            }
        });

        mAdFloatView = (AdFloatView)findViewById(R.id.ad_float);

        mAdFloatView.doAdRequest();

//        initTouch();

//        avatar = (ImageView) findViewById(R.id.avatar);
//
//        avatar.setOnTouchListener(mOnTouchListener);
    }

//    private void initTouch() {
//
//        screenWidth = Uikit.getRealWidth(this);
//
//        screenHeight = Uikit.getRealHeight(this);
//
//        mOnTouchListener = new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN: {
//                        startX = event.getRawX();
//                        startY = event.getRawY();
//                        break;
//                    }
//                    case MotionEvent.ACTION_MOVE: {
//                        // 计算偏移量
//                        int dx = (int) (event.getRawX() - startX);
//                        int dy = (int) (event.getRawY() - startY);
//                        // 计算控件的区域
//                        int left = v.getLeft() + dx;
//                        int right = v.getRight() + dx;
//                        int top = v.getTop() + dy;
//                        int bottom = v.getBottom() + dy;
//                        // 超出屏幕检测
//                        if (left < 0) {
//                            left = 0;
//                            right = v.getWidth();
//                        }
//                        if (right > screenWidth) {
//                            right = screenWidth;
//                            left = screenWidth - v.getWidth();
//                        }
//                        if (top < 0) {
//                            top = 0;
//                            bottom = v.getHeight();
//                        }
//                        if (bottom > screenHeight) {
//                            bottom = screenHeight;
//                            top = screenHeight - v.getHeight();
//                        }
//                        v.layout(left, top, right, bottom);
//                        startX = event.getRawX();
//                        startY = event.getRawY();
//                        break;
//                    }
//                    default:
//                        break;
//                }
//                return true;
//            }
//        };
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdFloatView.stop();
    }
}
