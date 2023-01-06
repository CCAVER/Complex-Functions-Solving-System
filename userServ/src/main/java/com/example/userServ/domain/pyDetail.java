package com.example.userServ.domain;

public class pyDetail {
    public String Pyname;

    public String getPyname() {
        return Pyname;
    }

    public void setPyname(String pyname) {
        Pyname = pyname;
    }

    public int id;
    public String uid;
    public String matha;
    public double mini;
    public double maxi;
    public double qal;
    public double wei;
    public double lent;

    public pyDetail(String pyname, int id, String uid, String matha, double mini, double maxi, double qal, double wei, double lent) {
        Pyname = pyname;
        this.id = id;
        this.uid = uid;
        this.matha = matha;
        this.mini = mini;
        this.maxi = maxi;
        this.qal = qal;
        this.wei = wei;
        this.lent = lent;
    }

    public pyDetail(int id, String uid, String matha, double mini, double maxi, double qal, double wei, double lent) {
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
