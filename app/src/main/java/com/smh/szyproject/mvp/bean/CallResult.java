package com.smh.szyproject.mvp.bean;

/**
 * author : smh
 * date   : 2020/9/22 14:41
 * desc   :
 */
public class CallResult {

    private int status;
    private String callId;
    private String phone;//给谁打的号
    private int duration;//时间


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
