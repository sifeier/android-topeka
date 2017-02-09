package com.buke.uikit;

import com.buke.fly.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * /**
 *
 * @author buke@taobao.com
 * @since 16/10/24
 */
public class FloatingView extends RelativeLayout {

    /**
     * 浮标广告回调接口
     */
    public interface onFloadingAdClickListener {

        void onAdClick();

        void onCloseClick();
    }

    private ImageView mFloatingAdImage;

    private ImageView mFloatingAdCloseImage;

    private Scroller mScroller;

    private int mLastX;

    private int mLastY;

    private int mScreenWidth;

    private int mScreenHeight;

    private int mStatusBarHeight;

    private int mActionBarHeight;

    private onFloadingAdClickListener mListener;

    private final int MIN_MOVE_OFF = 8;

    public FloatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public FloatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private RelativeLayout.LayoutParams mLayoutParams;
    public void initView(Context context) {
        //加载布局
        RelativeLayout convertView = (RelativeLayout) LayoutInflater.from(context)
                .inflate(R.layout.floating_ad_view, this);

        mLayoutParams = (RelativeLayout.LayoutParams)convertView.getLayoutParams();

        //初始化控件
        mFloatingAdImage = (ImageView) convertView.findViewById(R.id.iv_floating_ad);
        mFloatingAdCloseImage = (ImageView) convertView.findViewById(R.id.iv_floating_ad_close);

        //初始化Scroller
        mScroller = new Scroller(context);

        //获取屏幕宽度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断滑动是否完成
        if (mScroller.computeScrollOffset()) {
            //完成滑动，getCurrX()、getCurrY()为mScroller当前水平滚动的位置
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = x - mLastX;
                int moveY = y - mLastY;
                //保证浮标在屏幕能移动
                if ((rawY - y - mStatusBarHeight - mActionBarHeight + moveY < 0) || (
                        rawY - y + moveY + getHeight() > mScreenHeight)) {
                    scrollBy(-moveX, 0);
                } else {
                    scrollBy(-moveX, -moveY);
                }
                break;
            case MotionEvent.ACTION_UP:
                //手指离开时，滑动到屏幕一侧
                View viewGroup = ((View) getParent());
                //计算水平滚动的距离
                int offX = rawX > mScreenWidth / 2 ? -viewGroup.getScrollX() : rawX - x;
                //根据移动距离区分是点击事件还是滑动事件
                if (Math.abs(offX) < MIN_MOVE_OFF
                        && Math.abs(viewGroup.getScaleY()) < MIN_MOVE_OFF && mListener != null) {
                    if (x > (getWidth() - mFloatingAdCloseImage.getWidth())
                            && y < mFloatingAdCloseImage.getHeight()) {
                        mListener.onCloseClick();
                    } else {
                        mListener.onAdClick();
                    }
                } else {
//                    mScroller.startScroll(
//                            viewGroup.getScrollX(),
//                            viewGroup.getScrollY(),
//                            offX,
//                            0);
//                    invalidate();
                }
                break;
        }
        return true;
    }

    public void setOnFloatingAdClickListener(onFloadingAdClickListener listener) {
        if (listener != null) {
            this.mListener = listener;
        }
    }

    public void setExtraHeight(int statusBarHeight, int actionBarHeight) {
        mStatusBarHeight = statusBarHeight;
        mActionBarHeight = actionBarHeight;
    }



//    private void initTouch() {
//        mGestureDetector = new GestureDetector(mActivity, new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                if (!isMove) {
//                    if(mSingleTapListener != null) {
//                        mSingleTapListener.onSingleTap();
//                    }
//                }
//                return super.onSingleTapUp(e);
//            }
//        });
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
//                        isMove= false;
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
//                        if (dx >= 5 || dy >= 5) {
//                            isMove= true;
//                        }
//                        startX = event.getRawX();
//                        startY = event.getRawY();
//                        break;
//                    }
//                    default:
//                        break;
//                }
//                mGestureDetector.onTouchEvent(event);
//                return true;
//            }
//        };
//        setOnTouchListener(mOnTouchListener);
//    }
//
//    private OnSingleTapListener mSingleTapListener;
//
//    public void setOnSingleTapListener(OnSingleTapListener listener) {
//        mSingleTapListener = listener;
//    }
//
//    public interface OnSingleTapListener {
//        void onSingleTap();
//    }
}