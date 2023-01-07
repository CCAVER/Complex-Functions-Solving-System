package com.example.userServ.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class authControl {
    @RequestMapping("getAuth")
    public Map<String, String> mywork(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int c=0;//计数器
        Map<String, String> map = new HashMap<String, String>();
        for (Object auth : authentication.getAuthorities()) {
            System.out.println("the auth in getter:" + auth.toString());
            map.put(String.valueOf(c),auth.toString());
            c++;
        }
        return map;
    }
}
