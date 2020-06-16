package com.smh.szyproject.mvp.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * author : smh
 * date   : 2020/4/27 11:15
 * desc   :
 */
@Table(name = "ZmContact")
public class ZmContact {

    @Column(name = "name", isId = true, autoGen = false)
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
