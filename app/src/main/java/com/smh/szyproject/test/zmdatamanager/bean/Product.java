package com.smh.szyproject.test.zmdatamanager.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "product")
public class Product {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "name")
    private String name; // 商品名称



    @Column(name = "generalAgencyPrice")
    private float generalAgencyPrice; // 总代价

    @Column(name = "agentPrice")
    private float agentPrice; // 特代价

    @Column(name = "retail")
    private float retail; // 零售价

    @Column(name = "extra")
    private String extra; // 备注信息

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product() {

    }

    public Product(String name, float generalAgencyPrice, float agentPrice,float retail) {
        this.name = name;
        this.generalAgencyPrice = generalAgencyPrice;
        this.agentPrice = agentPrice;
        this.retail = retail;

    }

    public float getRetail() {
        return retail;
    }

    public void setRetail(float retail) {
        this.retail = retail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGeneralAgencyPrice() {
        return generalAgencyPrice;
    }

    public void setGeneralAgencyPrice(float generalAgencyPrice) {
        this.generalAgencyPrice = generalAgencyPrice;
    }

    public float getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(float agentPrice) {
        this.agentPrice = agentPrice;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
