package com.buke.view;

import com.google.samples.apps.topeka.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by buke on 16/4/11.
 */
public class ColorTextView extends TextView {

    public static final HashMap<String, String> COLORMAPS = new HashMap<>(5);

    static {
        COLORMAPS.put("1", "0xAAAAAA");
        COLORMAPS.put("2", "0xBBBBBB");
        COLORMAPS.put("3", "0xCCCCC");
        COLORMAPS.put("4", "0xDDDDDD");
        COLORMAPS.put("5", "0x000000");
    }

    private RectF mBgRectF;

    private Paint mBgPaint;

    private int mBgColor;

    public ColorTextView(Context context) {
        this(context, null);
    }

    public ColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        mBgColor = color;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mBgRectF = new RectF();
        mBgRectF.left = getLeft();
        mBgRectF.right = getRight();
        mBgRectF.bottom = getBottom();
        mBgRectF.top = getTop();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getContext().getColor(mBgColor));
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        canvas.drawRoundRect(mBgRectF, getMeasuredWidth()/2, getMeasuredHeight()/2, paint);
    }
}
