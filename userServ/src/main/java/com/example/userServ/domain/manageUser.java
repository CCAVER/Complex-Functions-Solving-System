package com.example.userServ.domain;

public class manageUser {
    String uid;
    String username;
    String authority;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public manageUser() {
    }

    public manageUser(String uid, String username, String authority) {
        this.uid = uid;
        this.username = username;
        this.authority = authority;
    }
}
