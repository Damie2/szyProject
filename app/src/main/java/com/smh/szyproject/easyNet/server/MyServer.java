package com.smh.szyproject.easyNet.server;

import com.hjq.http.config.IRequestServer;
import com.smh.szyproject.Constants;

/**
 * author : smh
 * date   : 2020/10/14 14:51
 * desc   :
 */
public class MyServer implements IRequestServer {
    @Override
    public String getHost() {
        return Constants.URL;
    }
}
