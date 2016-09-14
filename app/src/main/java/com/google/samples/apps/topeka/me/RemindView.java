package com.google.samples.apps.topeka.me;

import com.google.samples.apps.topeka.BuildConfig;
import com.google.samples.apps.topeka.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Common view used for new function Remind or emphasis
 *
 * 显示逻辑: enable 优先级最高 为true时显示，否则不显示
 * enable: 却省时为false, 因为很多item是复用的，需要时代码中动态设置为true
 * version: 限制版本，如果为限制版本则enable true，否则false
 * hasShow: 表示当前是否已经显示过
 * remindId: version+remindId 需具备唯一性, 可以配置，或者代码中设置
 *
 * @author buke@taobao.com
 * @since 16/8/24
 */
public class RemindView extends TextView {

    private static final String TAG = "RemindView";

    private boolean hasShow = false;

    private String remindId = "";

    private boolean enable = false;

    private String limitVersion = "";

    public RemindView(Context context) {
        super(context);
        initView();
    }

    public RemindView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemindView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public RemindView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray a = context
                .obtainStyledAttributes(attrs, R.styleable.RemindView, defStyleAttr, defStyleRes);
        try {
            limitVersion = a.getString(R.styleable.RemindView_limitVersion);

            remindId = a.getString(R.styleable.RemindView_remindId);

            enable = a.getBoolean(R.styleable.RemindView_enable, false);

        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }

        a.recycle();

        initView();
    }


    private void initView() {
        if (TextUtils.isEmpty(limitVersion)) {
            throw new IllegalArgumentException(TAG + "version must not be null");
        }

        if (TextUtils.isEmpty(remindId)) {
            hasShow = true;
        } else {
            hasShow = PreferenceManager.getDefaultSharedPreferences(getContext())
                    .getBoolean(makeRemindKey(), false);
        }

        updateShowStatus();

    }

    public void saveShowStatus() {
        if (!hasShow) {
            hasShow = true;
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit()
                    .putBoolean(makeRemindKey(), true).apply();
            updateShowStatus();
        }
    }

    public void enable() {
        enable = true;
        updateShowStatus();
    }

    public void disable() {
        enable = false;
        updateShowStatus();
    }

    public void setRemindId(String id) {
        remindId = id;

        if (TextUtils.isEmpty(remindId)) {
            hasShow = true;
        } else {
            hasShow = PreferenceManager.getDefaultSharedPreferences(getContext())
                    .getBoolean(makeRemindKey(), false);
        }

        //更新状态
        updateShowStatus();
    }

    private void updateShowStatus() {
        if (enable && !hasShow && BuildConfig.VERSION_NAME.contains(limitVersion)) {
            setVisibility(View.VISIBLE);
        } else {
            setVisibility(View.GONE);
        }
    }



    /**
     * RemindKey should be independent
     */
    private String makeRemindKey() {
        return new StringBuilder(32).append(limitVersion).append("_").append(remindId).toString();
    }

}
