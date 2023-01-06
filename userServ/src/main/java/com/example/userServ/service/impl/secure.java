package com.example.userServ.service.impl;

import com.example.userServ.dao.AccountDao;
import com.example.userServ.domain.user;
import com.example.userServ.service.MySecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("MySecure")
public class secure implements MySecure {
    @Autowired
    AccountDao accountDao;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();}

    @Autowired // 注入 security的 encoder
    BCryptPasswordEncoder encoder;

//    @Override
//    public String[] login(String username,String password){
//        if(username!=null&&username!="") {
//            user tmp = accountDao.getU(username);
//            return new String[]{String.valueOf(encoder.matches(password, tmp.getPassword())), String.valueOf(tmp.getId())};
//        }
//        else return new String[]{"false",""};
//    }
    @Override
    public String[] login(String username){
    if(username!=null&&username!="") {
        user tmp = accountDao.getU(username);
        return new String[]{"true", String.valueOf(tmp.getId())};
    }
    else return new String[]{"false",""};
}
    @Override
    public String encryptPassword(String password) {
        // BCryptPasswordEncoder 加密
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public String add(String username, String password){
        String userId=accountDao.add2(username,encryptPassword(password));
        return userId;
    }
}
