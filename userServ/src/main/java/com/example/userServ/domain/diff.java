package com.example.userServ.domain;

public class diff {
    byte[] diffimg;
    byte[] fximg;
    String inf;

    public byte[] getDiffimg() {
        return diffimg;
    }

    public void setDiffimg(byte[] diffimg) {
        this.diffimg = diffimg;
    }

    public byte[] getFximg() {
        return fximg;
    }

    public void setFximg(byte[] fximg) {
        this.fximg = fximg;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
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

    String diff;
    String fx;
    int id;
    String uid;
}
