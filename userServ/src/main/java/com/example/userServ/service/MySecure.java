package com.example.userServ.service;

public interface MySecure {
    String[] login(String username);
    String add(String username,String password);
    String encryptPassword(String password);

}
