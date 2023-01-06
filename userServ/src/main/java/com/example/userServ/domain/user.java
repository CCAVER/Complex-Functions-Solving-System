package com.example.userServ.domain;

public class user {
    String password;
    int uid;
    String username;
    int valid;

    public int getUid() {
        return uid;
    }

    public user(String password, int uid, String username, int valid) {
        this.password = password;
        this.uid = uid;
        this.username = username;
        this.valid = valid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public user() {
    }


    public user(String password, int uid, String username) {
        this.password = password;
        this.uid = uid;
        this.username = username;
    }

    public int getId() {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
