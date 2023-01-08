package com.example.userServ.controller;

import com.example.userServ.config.RabbitMQConfig;
import com.example.userServ.service.AcServ;
import com.example.userServ.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("pyConn")
public class RabbitMQController {
    @Autowired
    RabbitMQService rabbitMQService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AcServ acServ;
    /**
     * 发送消息
     * @author java技术爱好者
     */
//    @RequestMapping("/RB0")
//    public String sendMsg0(@RequestParam(name = "msg") String msg) throws Exception {
//        return rabbitMQService.sendMsg(msg);
//    }
//    @RequestMapping("/RB")
//    public String sendMsg(HttpSession session) throws Exception {
//        return rabbitMQService.sendMsg((String) session.getAttribute("uid"));
//    }
    @RequestMapping(value = "pyReq/lat", method = RequestMethod.POST)
    void golatex(@RequestBody String uid, @RequestParam(value = "matha") String matha) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid",uid);
        map.put("matha",matha);
        //rabbitTemplate.convertAndSend("latex", message);
        //return rabbitMQService.sendMsg(map);
        /**
         * 发送消息，参数说明：
         * String exchange：交换器名称。
         * String routingKey：路由键。
         * Object object：发送内容。
         */
        rabbitTemplate.convertAndSend("latex", map);
        System.out.println("消息发送成功！");

    };
    @PostMapping (value = "/diff")//暂时弃用
    public void diff(String matha,HttpSession session) throws Exception{
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        if(matha==null){matha="sin(x)";}
        System.out.println("the data in diff is: uid0:"+uid+" matha:"+matha);

//        Map<String, byte[]> map = new HashMap<String, byte[]>();

        //String newid=feAc.goDiff(uid, matha);
        Map<String, String> infMap = new HashMap<String, String>();
        infMap.put("uid",uid);
        infMap.put("matha",matha);
        String newid= (String) rabbitTemplate.convertSendAndReceive("diff", infMap);
        session.setAttribute("diffID",newid);

//        byte[] data=acServ.genDiff(newid);
//        session.setAttribute("diffID",newid);
//
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        map.put("img", data);
//        //System.out.println(data);
//        //return new ResponseEntity<>(data, headers, HttpStatus.OK);
//        return map;
    }
    @PostMapping (value = "/getDiff")
    public Map<String, byte[]> getDiff(String matha,HttpSession session) throws Exception{
        String uid="0";
        //uid="1";matha="sin(x)";
        try{uid= (String) session.getAttribute("uid");}catch (Exception e){e.printStackTrace();}
        if(matha==null){matha="sin(x)";}
        System.out.println("the data in diff is: uid0:"+uid+" matha:"+matha);

        Map<String, byte[]> map = new HashMap<String, byte[]>();
        Map<String, String> infMap = new HashMap<String, String>();
        infMap.put("uid",uid);
        infMap.put("matha",matha);
        String newid= (String) rabbitTemplate.convertSendAndReceive("diff", infMap);


        byte[] data=acServ.genDiff(newid);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        map.put("img", data);
        //System.out.println(data);
        //return new ResponseEntity<>(data, headers, HttpStatus.OK);
        return map;
    }
}
