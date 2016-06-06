package com.google.samples.apps.topeka.me;

import com.google.samples.apps.topeka.R;
import com.google.samples.apps.topeka.widget.MaterialSpinner;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by buke on 16/3/30.
 */
public class DesignMeActivity extends Activity {

    private static final String TAG = "DesignMeActivity";

    private MaterialSpinner mMaterialSpinner;

    private com.rey.material.widget.Spinner mSpinner;

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

        ColorTextView colorTextView = (ColorTextView)findViewById(R.id.content_tv);
        colorTextView.setTextColor(getColor(R.color.green));

        mMaterialSpinner = (MaterialSpinner) findViewById(R.id.spinner_menu_mode);

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

    ExpandableTextView mExpandableTextView;

    private void initFoldableTv() {
        mExpandableTextView = (ExpandableTextView) findViewById(R.id.expand_view);
        mExpandableTextView.setText(getString(R.string.long_string));
        mExpandableTextView.setUnreadStatus();
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

    public class InstanceFamilyComparator<T> implements Comparator<T> {

        @Override
        public int compare(T lhs, T rhs) {
            return 0;
        }
    }
}
