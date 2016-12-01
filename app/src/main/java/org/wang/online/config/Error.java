package org.wang.online.config;

/**
 * 错误
 * Created by 王冰 on 2016/11/29.
 */

public class Error extends Throwable {
    private int code;

    public Error(int theCode) {
        super();
        this.code = theCode;
    }

    public Error(int theCode, String theMessage) {
        super(theMessage);
        this.code = theCode;
    }

    public int getCode() {
        return this.code;
    }
}
