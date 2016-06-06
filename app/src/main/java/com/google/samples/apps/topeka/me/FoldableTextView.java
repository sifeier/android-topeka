package com.google.samples.apps.topeka.me;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringRes;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.samples.apps.topeka.R;

/**
 * Created by buke on 16/3/31.
 */
public class FoldableTextView extends LinearLayout implements View.OnClickListener {

    /**
     * 显示遮罩时的行数
     */
    private static final int SHADER_MAX_LINE = 3;

    private static final int UNLIMITED_LINE = 100;

    /**
     * 缺省显示行数限制
     */
    private int maxLine;

    private int shaderMaxLine;

    /**
     * text完整显示时的行数
     */
    private int realLine;

    private boolean flag;

    private View mShader;

    private CheckedTextView mTextView;

    public FoldableTextView(Context context) {
        this(context, null);
    }

    public FoldableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FoldableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FoldableTextView(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context
                .obtainStyledAttributes(attrs, R.styleable.FoldableTextView, defStyleAttr,
                        defStyleRes);
        maxLine = a.getInt(R.styleable.FoldableTextView_maxline, SHADER_MAX_LINE);
        a.recycle();

        View root = inflate(context, R.layout.layout_foldable_textview, null);
        mTextView = (CheckedTextView) root.findViewById(R.id.content_tv);
//        mTextView.setMaxLines(maxLine);
        mShader = root.findViewById(R.id.shader);
    }

    public final void setText(@StringRes int resid) {
        mTextView.setText(resid);
        realLine = mTextView.getLineCount();
        requestLayout();
    }

    public final void setText(CharSequence text) {
        mTextView.setText(text);
        realLine = mTextView.getLineCount();
        requestLayout();
    }

    public void showUnreadState() {
        mTextView.setMaxLines(maxLine + 1);
        if (mShader.getVisibility() != View.VISIBLE) {
            mShader.setVisibility(View.VISIBLE);
        }
    }

    public void showReadState() {
        mTextView.setMaxLines(maxLine);
        if (mShader.getVisibility() == View.VISIBLE) {
            mShader.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if(realLine <= maxLine) {
            return;
        }
        mShader.setVisibility(View.GONE);
        flag = false;
        mTextView.toggle();
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!flag) {
            flag = true;
            if (realLine >= maxLine) {
                post(new FoldRunnable());
            }
        }
        super.onLayout(changed, l, t, r, b);
    }

    class FoldRunnable implements Runnable {

        @Override
        public void run() {
            if (mTextView.isChecked()) {
                mTextView.setMaxLines(UNLIMITED_LINE);
            } else {
                mTextView.setMaxLines(maxLine);
            }
        }
    }
}
