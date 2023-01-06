package com.example.finalwork4.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "imginf")  //表名
public class MongoPyDetail implements Serializable {
    @Id //绑定每个表中的_id字段
    public int id;

    public String name;

    public String getName() {
        return name;
    }

    public void setname(String name) {
        name = name;
    }

    public String uid;
    public String matha;
    public double mini;

    public MongoPyDetail() {
    }

    public double maxi;
    public double qal;
    public double wei;
    public double lent;

    public MongoPyDetail(int id) {
        this.name = "name";
        this.id = id;
        this.uid = "uid";
        this.matha = "matha";
        this.mini = 0;
        this.maxi = 0;
        this.qal = 0;
        this.wei = 0;
        this.lent = 0;

    }

    public MongoPyDetail(String name, int id, String uid, String matha, double mini, double maxi, double qal, double wei, double lent) {
        this.name = name;
        this.id = id;
        this.uid = uid;
        this.matha = matha;
        this.mini = mini;
        this.maxi = maxi;
        this.qal = qal;
        this.wei = wei;
        this.lent = lent;
    }

    public MongoPyDetail(int id, String uid, String matha, double mini, double maxi, double qal, double wei, double lent) {
        this.id = id;
        this.uid = uid;
        this.matha = matha;
        this.mini = mini;
        this.maxi = maxi;
        this.qal = qal;
        this.wei = wei;
        this.lent = lent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMatha() {
        return matha;
    }

    public void setMatha(String matha) {
        this.matha = matha;
    }

    public double getMini() {
        return mini;
    }

    public void setMini(double mini) {
        this.mini = mini;
    }

    public double getMaxi() {
        return maxi;
    }

    public void setMaxi(double maxi) {
        this.maxi = maxi;
    }

    public double getQal() {
        return qal;
    }

    public void setQal(double qal) {
        this.qal = qal;
    }

    public double getWei() {
        return wei;
    }

    public void setWei(double wei) {
        this.wei = wei;
    }

    public double getLent() {
        return lent;
    }

    public void setLent(double lent) {
        this.lent = lent;
    }
}
