package com.example.userServ.controller;

import com.example.userServ.service.AcServ;
import com.example.userServ.service.FeAc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lianying
 * @create 2020-11-22 3:43 下午
 **/
@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    FeAc feAc;
    @Autowired
    AcServ acServ;
    @Autowired
    RabbitMQController rb;

//    @RequestMapping("/rc")
//    public String getIndex(){
//        stringRedisTemplate.opsForValue().set("xiaocai", "888");
//        String res = stringRedisTemplate.opsForValue().get("xiaocai");
//        System.out.println(res);
//        return res;
//    }

    @PostMapping(value = "/latex")
    public Map<String, byte[]> latex(String matha, HttpSession session) throws Exception{
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        if(matha==null){matha="sin(x)";}
        System.out.println("the data in latex is: uid0:"+uid+" matha:"+matha);

        Map<String, byte[]> map = new HashMap<String, byte[]>();

        //feAc.golatex(uid, matha);
        //rb.golatex(uid,matha);

        byte[] finalUid = uid.getBytes(StandardCharsets.UTF_8);
        // 使用了lambda表达式
        byte[] data = (byte[]) redisTemplate.execute((RedisConnection redisConnection) -> redisConnection.get(finalUid));

//        byte[] data=stringRedisTemplate.execute(new RedisCallback<byte[]>() {
//            @Override
//            public byte[] doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                // 传入byte[]类型的key，获取byte[]类型的value
//                byte[] bytes = redisConnection.get(finalUid);
//                return bytes;
//            }
//        });

        //byte[] data=acServ.genLatex(uid);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        map.put("img", data);
        //System.out.println(data);
        //return new ResponseEntity<>(data, headers, HttpStatus.OK);
        return map;
    }
    @PostMapping(value = "/subLatex")
    public void subLatex(String matha, HttpSession session) throws Exception{
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        if(matha==null){matha="sin(x)";}
        System.out.println("the data in latex is: uid0:"+uid+" matha:"+matha);

        //feAc.golatex(uid, matha);
        rb.golatex(uid,matha);

//        byte[] finalUid = uid.getBytes(StandardCharsets.UTF_8);
//        // 使用了lambda表达式
//        byte[] data = (byte[]) redisTemplate.execute((RedisConnection redisConnection) -> redisConnection.get(finalUid));
//
////        byte[] data=stringRedisTemplate.execute(new RedisCallback<byte[]>() {
////            @Override
////            public byte[] doInRedis(RedisConnection redisConnection) throws DataAccessException {
////                // 传入byte[]类型的key，获取byte[]类型的value
////                byte[] bytes = redisConnection.get(finalUid);
////                return bytes;
////            }
////        });
//
//        //byte[] data=acServ.genLatex(uid);
//
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        map.put("img", data);
//        //System.out.println(data);
//        //return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}