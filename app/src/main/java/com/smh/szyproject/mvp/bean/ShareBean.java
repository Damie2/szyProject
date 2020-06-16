package com.smh.szyproject.mvp.bean;

/**
 * author : smh
 * date   : 2020/3/24 11:53
 * desc   :
 */
public class ShareBean {

    /**
     * app_id : wx50d801314d9eb858
     * application_name : 今日头条
     * id : 5
     * limit : 0
     * package_name : com.ss.android.article.news
     * page : 0
     * weight : 50
     */

    private String app_id;
    private String application_name;
    private int id;
    private int limit;
    private String package_name;
    private int page;
    private int weight;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
