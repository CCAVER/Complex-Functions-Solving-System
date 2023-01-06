package com.example.userServ.domain;

import java.io.Serializable;

public class account implements Serializable {
    int id;
    String status;
    int num;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "account{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", num=" + num +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
