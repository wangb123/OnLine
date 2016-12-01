package org.wang.online.util;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.wang.online.OnLine;


/**
 * Toast统一管理类
 */
public class ToastUtils {

    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Toast topToast = null;
    private static Object synObj = new Object();

    public static void showMessage(   String msg) {
        showMessage(  msg, Toast.LENGTH_SHORT);
    }

    public static void showMessageLong(   String msg) {
        showMessage(  msg, Toast.LENGTH_LONG);
    }

    public static void showMessage(  int msg) {
        showMessage( msg, Toast.LENGTH_SHORT);
    }

    public static void showMessageLong( int msg) {
        showMessage( msg, Toast.LENGTH_LONG);
    }

    public static void showMessage( final int msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                toast.setText(msg);
                                toast.setDuration(len);
                            } else {
                                toast = Toast.makeText(OnLine.getInstance().getApplicationContext(), msg, len);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }


    public static void showMessage(  final String msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                toast.setText(msg);
                                toast.setDuration(len);
                            } else {
                                toast = Toast.makeText(OnLine.getInstance().getApplicationContext(), msg, len);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    public static void cancelCurrentToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}