package com.buke;

import com.google.samples.apps.topeka.R;
import com.google.samples.apps.topeka.widget.MaterialSpinner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.samples.apps.topeka.tab.PagerSlidingTabStrip;

import com.buke.view.ExpandableTextView;
import com.buke.view.RemindView;
import com.rey.material.widget.Button;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by buke on 16/3/30.
 */
public class DesignMeActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "DesignMeActivity";

    private MaterialSpinner mMaterialSpinner;

    private PagerSlidingTabStrip mSlidingTabStrip;

    private com.rey.material.widget.Spinner mSpinner;

    private Button remindBtn;

    private RemindView mRemindView;

    // Menu mode spinner choices
    // This list must match the list found in samples/ApiDemos/res/values/arrays.xml
    final static int MENUMODE_SEARCH_KEY = 0;

    final static int MENUMODE_MENU_ITEM = 1;

    final static int MENUMODE_TYPE_TO_SEARCH = 2;

    final static int MENUMODE_DISABLED = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_spinner);

        mySortInstanceType();

//        ColorTextView colorTextView = (ColorTextView)findViewById(R.id.color_tv);
//        colorTextView.setTextColor(getColor(R.color.green));

        mSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.indicator);

        mMaterialSpinner = (MaterialSpinner) findViewById(R.id.spinner_menu_mode);

        mRemindView = (RemindView) findViewById(R.id.remind_view);

        // Populate items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.search_menuModes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMaterialSpinner.setAdapter(adapter);

        // Create listener for the menu mode dropdown.  We use this to demonstrate control
        // of the default keys handler in every Activity.  More typically, you will simply set
        // the default key mode in your activity's onCreate() handler.
        mMaterialSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        if (position == MENUMODE_TYPE_TO_SEARCH) {
                            setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
                        } else {
                            setDefaultKeyMode(DEFAULT_KEYS_DISABLE);
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        setDefaultKeyMode(DEFAULT_KEYS_DISABLE);
                    }
                });

        mSpinner = (com.rey.material.widget.Spinner) findViewById(R.id.material_spinner);

        mSpinner.setAdapter(adapter);

        // Create listener for the menu mode dropdown.  We use this to demonstrate control
        // of the default keys handler in every Activity.  More typically, you will simply set
        // the default key mode in your activity's onCreate() handler.
        mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner parent, View view, int position, long id) {
                if (position == MENUMODE_TYPE_TO_SEARCH) {
                    setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
                } else {
                    setDefaultKeyMode(DEFAULT_KEYS_DISABLE);
                }
            }
        });

        initFoldableTv();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.binder_notify:
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancelAll();
                break;
            case R.id.binder_download:
                DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(new DownloadManager.Request(Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3jWa-albpF6-lz1b-BrOP_nIgrfJGBJ09zrYxeBBGk8mCc3qmIgwi2cM")).setTitle("pic"));
                break;
            case R.id.binder_sennsor:
                SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
                sensorManager.getSensorList(Sensor.TYPE_LIGHT);
                break;
            default:
                break;
        }
    }

    ExpandableTextView mExpandableTextView;

    private void initFoldableTv() {
        mExpandableTextView = (ExpandableTextView) findViewById(R.id.expand_view);
        mExpandableTextView.setText(getString(R.string.long_string));
        mExpandableTextView.setUnreadStatus();
    }

    private void initIndicator() {

    }

    /**
     * t s m c n e  排序
     */
    private void mySortInstanceType() {
        HashSet<String> sets = new HashSet<>();
        sets.add("ecs.s1");
        sets.add("ecs.c1");
        sets.add("ecs.t1");
        sets.add("ecs.m1");
        sets.add("ecs.s2");
        sets.add("ecs.m2");
        sets.add("ecs.s3");
        sets.add("ecs.c2");

        List<String> families = new ArrayList<>(sets);
        Collections.sort(families, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                int lv = 0, rv = 0;
                int max = 100;
                int start = 0;

                if (TextUtils.isEmpty(lhs)) {
                    lv = 100; //最大
                } else if (lhs.startsWith("ecs.t")) {
                    lv = start + 1;
                } else if (lhs.startsWith("ecs.s")) {
                    lv = start + 2;
                } else if (lhs.startsWith("ecs.m")) {
                    lv = start + 3;
                } else if (lhs.startsWith("ecs.c")) {
                    lv = start + 4;
                } else if (lhs.startsWith("ecs.n")) {
                    lv = start + 5;
                } else if (lhs.startsWith("ecs.e")) {
                    lv = start + 6;
                }

                if (TextUtils.isEmpty(rhs)) {
                    rv = 100; //最大
                } else if (rhs.startsWith("ecs.t")) {
                    rv = start + 1;
                } else if (rhs.startsWith("ecs.s")) {
                    rv = start + 2;
                } else if (rhs.startsWith("ecs.m")) {
                    rv = start + 3;
                } else if (rhs.startsWith("ecs.c")) {
                    rv = start + 4;
                } else if (rhs.startsWith("ecs.n")) {
                    lv = start + 5;
                } else if (rhs.startsWith("ecs.e")) {
                    lv = start + 6;
                }

                if(lv == rv) {
                    return lhs.compareTo(rhs);
                }

                return lv - rv;
            }
        });

        Log.e(TAG, families.toString());
    }

    public void onRemindButtonClick(View v) {
        mRemindView.saveShowStatus();
    }

    public class InstanceFamilyComparator<T> implements Comparator<T> {
        @Override
        public int compare(T lhs, T rhs) {
            return 0;
        }
    }
}
