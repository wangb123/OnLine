package org.wang.online.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.wang.online.OnLine;

/**
 * Created by 王冰 on 2016/11/30.
 */

public class Utils {
    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) OnLine.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
