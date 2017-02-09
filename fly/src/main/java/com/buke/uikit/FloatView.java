package com.buke.uikit;

import com.buke.fly.R;
import com.buke.util.Uikit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author buke@taobao.com
 *
 * @since 16/10/24
 */
public class FloatView extends RelativeLayout {

    private static final String TAG = "FloatView";

    private Context mActivity;

    protected LayoutInflater mInflater;

    protected View mView;

    private int screenWidth, screenHeight;

    private float startX = 0, startY = 0;

    private boolean isMove;

    View.OnTouchListener mOnTouchListener;

    private GestureDetector mGestureDetector;

    public FloatView(Context context) {
        super(context);
        init(context);
    }

    public FloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(Context context) {
        mActivity = context;
        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(getLayoutId(), null);

        try {
            screenWidth = Uikit.getRealWidth((Activity) context);
            screenHeight = Uikit.getRealHeight((Activity) context);
        } catch (Exception ex) {

        }

        addView(mView);

        initTouch();

        initParam(context);

        initView(context);

    }


    /**
     * should be Override in child
     *
     * @param context must be instance of Activity
     */
    protected void initParam(Context context) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        layoutParams.leftMargin = Uikit.getRealWidth((Activity) context) - 200;
        layoutParams.topMargin = Uikit.getRealHeight((Activity) context) - 400;
        mView.setLayoutParams(layoutParams);
    }

    /**
     * should be Override in child
     *
     * @param context
     */
    protected void initView(Context context) {
        setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap() {
                Toast.makeText(mActivity, "float clicked!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected
    @LayoutRes
    int getLayoutId() {
        return R.layout.ly_float_view;
    }

    private void initTouch() {
        mGestureDetector = new GestureDetector(mActivity,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        if (!isMove && mSingleTapListener != null) {
                            mSingleTapListener.onSingleTap();
                        }
                        return super.onSingleTapUp(e);
                    }
                });

        mOnTouchListener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN: {
                        startX = event.getRawX();
                        startY = event.getRawY();
                        isMove = false;
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        // 计算偏移量
                        int dx = (int) (event.getRawX() - startX);
                        int dy = (int) (event.getRawY() - startY);
                        // 计算控件的区域
                        int left = v.getLeft() + dx;
                        int right = v.getRight() + dx;
                        int top = v.getTop() + dy;
                        int bottom = v.getBottom() + dy;
                        // 超出屏幕检测
                        if (left < 0) {
                            left = 0;
                            right = v.getWidth();
                        }
                        if (right > screenWidth) {
                            right = screenWidth;
                            left = screenWidth - v.getWidth();
                        }
                        if (top < 0) {
                            top = 0;
                            bottom = v.getHeight();
                        }
                        if (bottom > screenHeight) {
                            bottom = screenHeight;
                            top = screenHeight - v.getHeight();
                        }

                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mView.getLayoutParams();
                        layoutParams.leftMargin = left;
                        layoutParams.topMargin = top;
                        mView.setLayoutParams(layoutParams);

//                        mView.layout(left, top, right, bottom);
                        if (dx >= 5 || dy >= 5) {
                            isMove = true;
                        }
                        startX = event.getRawX();
                        startY = event.getRawY();
                        break;
                    }
                    default:
                        break;
                }
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        };

        mView.setOnTouchListener(mOnTouchListener);

    }

    private OnSingleTapListener mSingleTapListener;

    public void setOnSingleTapListener(OnSingleTapListener listener) {
        mSingleTapListener = listener;
    }

    public interface OnSingleTapListener {

        void onSingleTap();

    }

}
