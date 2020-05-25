package com.smh.szyproject.bean;

/**
 * author : smh
 * date   : 2020/5/19 16:33
 * desc   :
 */
public class getStatusResult {

    /**
     * code : 200
     * message : success
     */

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
