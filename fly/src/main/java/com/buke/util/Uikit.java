package com.buke.util;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;

/**
 * @author buke@taobao.com
 * @since 16/10/24
 */
public class Uikit {

    public static int getRealWidth(Activity aty) {
        Point point = getRealSize(aty);
        return point.x;
    }

    public static int getRealHeight(Activity aty) {
        Point point = getRealSize(aty);
        return point.y;
    }

    public static Point getRealSize(Activity aty) {
        Point point = new Point();
        Display display = aty.getWindowManager().getDefaultDisplay();
        if(Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(point);
        } else {
            display.getSize(point);
        }

        return point;
    }
}
