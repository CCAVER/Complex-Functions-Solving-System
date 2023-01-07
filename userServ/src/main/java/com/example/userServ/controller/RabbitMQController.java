package com.example.userServ.controller;

import com.example.userServ.config.RabbitMQConfig;
import com.example.userServ.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    /**
     * 发送消息
     * @author java技术爱好者
     */
    @RequestMapping("/RB0")
    public String sendMsg0(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsg(msg);
    }
    @RequestMapping("/RB")
    public String sendMsg(HttpSession session) throws Exception {
        return rabbitMQService.sendMsg((String) session.getAttribute("uid"));
    }
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
        rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING, map);
        System.out.println("消息发送成功！");

    };
}
