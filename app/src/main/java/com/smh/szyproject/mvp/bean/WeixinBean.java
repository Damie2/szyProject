package com.smh.szyproject.mvp.bean;

/**
 * Create by smh on 2019/2/22.
 */
public class WeixinBean {

    /**
     * access_token : 18_vKJv73wr7nnNulDo9dzXo7Z5MXXnZ6EQVxrkNo4d246ojuJVZ_a3JNex8oey_MQh5jotvZtsqNFfLZQUioNat3iDMh1MQF1rWSkuRVu7a3o
     * expires_in : 7200
     * refresh_token : 18_kSEz5ZsM8YoOMfnn5Z6mUduwNqeE7Xdi1zHWYm-Ha7kw5b58nXq51Ol6HUPFy6KaLWDrv4cAICS80MemQquVNo33P-ZouyCR6tnFtDayDeE
     * openid : omHUE1fPtk5pkLfzNf8st037OhXo
     * scope : snsapi_userinfo
     * unionid : o0O5i5yMAH6KphQY2vL4pwWvrTuc
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
