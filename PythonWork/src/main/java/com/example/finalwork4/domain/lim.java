package com.example.finalwork4.domain;

public class lim {
    int id=0;
    String uid;
    String name;
    String matha;
    String result;
    String lim;
    String sym;

    public lim(String uid, String matha, String lim, String sym) {
        this.uid = uid;
        this.matha = matha;
        this.lim = lim;
        this.sym = sym;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatha() {
        return matha;
    }

    public void setMatha(String matha) {
        this.matha = matha;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLim() {
        return lim;
    }

    public void setLim(String lim) {
        this.lim = lim;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

}
