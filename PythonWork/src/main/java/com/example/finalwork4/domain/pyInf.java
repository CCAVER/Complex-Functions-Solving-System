package com.example.finalwork4.domain;

import java.io.Serializable;

public class pyInf implements Serializable {
    public double quality;

    public pyInf(double quality, String fname, String uid, String matha, double lent, double hei, String maxi, String mini) {
        this.quality = quality;
        this.fname = fname;
        this.uid = uid;
        this.matha = matha;
        this.lent = lent;
        this.hei = hei;
        this.maxi = maxi;
        this.mini = mini;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public String fname;
    public String uid;
    public String matha="";
    public double lent=10;
    public double hei=10;

    public pyInf() {
    }

    public pyInf(String fname, String uid, String matha, double lent, double hei, String maxi, String mini) {
        this.fname = fname;
        this.uid = uid;
        this.matha = matha;
        this.lent = lent;
        this.hei = hei;
        this.maxi = maxi;
        this.mini = mini;
    }

    public double getLent() {
        return lent;
    }

    public void setLent(double lent) {
        this.lent = lent;
    }

    public double getHei() {
        return hei;
    }

    public void setHei(double hei) {
        this.hei = hei;
    }

    public String getMaxi() {
        return maxi;
    }

    public void setMaxi(String maxi) {
        this.maxi = maxi;
    }

    public String getMini() {
        return mini;
    }

    public void setMini(String mini) {
        this.mini = mini;
    }

    public String maxi="10";
    public String mini="-10";

    public pyInf(String fname, String uid, String matha) {
        this.fname = fname;
        this.uid = uid;
        this.matha = matha;
    }

    public pyInf(String fname, String uid) {
        this.fname = fname;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "pyInf{" +
                "fname='" + fname + '\'' +
                ", uid='" + uid + '\'' +
                ", matha='" + matha + '\'' +
                '}';
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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
}
