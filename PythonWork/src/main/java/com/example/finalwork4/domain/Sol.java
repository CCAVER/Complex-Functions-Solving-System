package com.example.finalwork4.domain;

public class Sol {
    int id;
    String uid;
    String name;
    String matha;
    String res;
    String type;
    String sym;
    String fuc;

    public Sol(String uid, String matha, String type, String sym, String fuc) {
        this.uid = uid;
        this.matha = matha;
        this.type = type;
        this.sym = sym;
        this.fuc = fuc;
        this.name=matha;
    }

    public String getFuc() {
        return fuc;
    }

    public void setFuc(String fuc) {
        this.fuc = fuc;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
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

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
