package com.smh.szyproject.mvp.bean;

import java.util.List;

/**
 * Create by smh on 2019/2/22.
 */
public class WeixinUserBean {

    /**
     * openid : omHUE1fPtk5pkLfzNf8st037OhXo
     * nickname : 邵民航
     * sex : 1
     * language : zh_CN
     * city : Fengtai
     * province : Beijing
     * country : CN
     * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erH8fXTxqZYR8bXG1Cd4mePLu1zrtgbKfZWCFwAJpJ1kztY1icCibG2tawcG0XIp5p8QAUVWS1KNSlg/132
     * privilege : []
     * unionid : o0O5i5yMAH6KphQY2vL4pwWvrTuc
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;
    private String name;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
