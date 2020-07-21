package com.smh.szyproject.test.zmdatamanager.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * author : smh
 * date   : 2020/7/20 15:26
 * desc   :
 */
@Table(name = "Order")
public class Order {
    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "name")
    private String name; // 商品名称

    @Column(name = "productId")
    private int productId;//商品Id

    @Column(name = "submitPerson")
    private String submitPerson; // 提交人

    @Column(name = "customer")
    private String customer; // 顾客名称

    @Column(name = "createTime")
    private long createTime; // 创建时间

    @Column(name = "status")
    private int status; // 状态

    @Column(name = "number")
    private int number; // 数量

    @Column(name = "extra")
    private String  extra; // 备注


    @Column(name = "generalAgencyPrice")
    private float generalAgencyPrice; // 总代价

    @Column(name = "agentPrice")
    private float agentPrice; // 特代价

    @Column(name = "retail")
    private float retail; // 零售价



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSubmitPerson() {
        return submitPerson;
    }

    public void setSubmitPerson(String submitPerson) {
        this.submitPerson = submitPerson;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public float getRetail() {
        return retail;
    }

    public void setRetail(float retail) {
        this.retail = retail;
    }
}
