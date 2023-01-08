package com.example.userServ.controller;

import com.example.finalwork4.domain.pyInf;
import com.example.userServ.domain.diff;
import com.example.userServ.service.AcServ;
import com.example.userServ.service.FeAc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ImgWork {
    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @Autowired
    AcServ acServ;

    @Autowired
    FeAc feAc;

    @RequestMapping(value = "/image/{image_name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name, final HttpServletResponse response, HttpSession session) throws Exception{
        pyInf pi= (pyInf) session.getAttribute("pi");

        int newid= Integer.parseInt(String.valueOf(session.getAttribute("newid")));

        response.setContentType("image/png");
        response.setCharacterEncoding("UTF-8");

        byte[] data=acServ.genImg(newid);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PostMapping (value = "/latex0")
    public Map<String, byte[]> latex(String matha,HttpSession session) throws Exception{
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        if(matha==null){matha="sin(x)";}
        System.out.println("the data in latex is: uid0:"+uid+" matha:"+matha);

        Map<String, byte[]> map = new HashMap<String, byte[]>();

        feAc.golatex(uid, matha);

        byte[] data=acServ.genLatex(uid);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        map.put("img", data);
        //System.out.println(data);
        //return new ResponseEntity<>(data, headers, HttpStatus.OK);
        return map;
    }
    @PostMapping (value = "/diff0")
    public Map<String, byte[]> diff(String matha,HttpSession session) throws Exception{
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        if(matha==null){matha="sin(x)";}
        System.out.println("the data in diff is: uid0:"+uid+" matha:"+matha);

        Map<String, byte[]> map = new HashMap<String, byte[]>();

        String newid=feAc.goDiff(uid, matha);

        byte[] data=acServ.genDiff(newid);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        map.put("img", data);
        //System.out.println(data);
        //return new ResponseEntity<>(data, headers, HttpStatus.OK);
        return map;
    }
    @PostMapping("diffRead")
    public Map<String, byte[]> diffRead(String rowid,HttpSession session) throws Exception {
        byte[] data=acServ.genDiff(rowid);
        Map<String, byte[]> map = new HashMap<String, byte[]>();
        map.put("img", data);
        return map;
    }
}
