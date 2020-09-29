package com.smh.szyproject.net;

import com.smh.szyproject.Constants;

public class ApiAddress {

    //base地址
    public final static String api = Constants.URL;

    public final static String sendVersion = "upfile";

    public final static String getStatus = "/test/a";


    public final static String getResult = "index/test";


    //1.安装APP后发送请求
    public final static String sendParameter = "/v1/app/device";
    public final static String getName = "getName";


    //call 登录
    public final static String callLogin = "callLogin";

    public final static String callGetUser = "call";

    public final static String callSendResult = "callResponse";

}

