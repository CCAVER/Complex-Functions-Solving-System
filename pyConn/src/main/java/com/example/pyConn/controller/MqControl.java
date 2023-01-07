package com.example.pyConn.controller;

import com.example.pyConn.service.pyServ;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RabbitListener(queues = {"topic"})//是userServ里RabbitMQConfig里的配置
public class MqControl {
    @Autowired
    pyServ pyServ;
    @RabbitHandler
    //@RabbitListener(queuesToDeclare = @Queue("latex"))
    public void latex(Map message) throws Exception {
        System.out.println("the latex inf is:"+(String) message.get("uid")+(String) message.get("matha"));
        pyServ.latex((String) message.get("uid"), (String) message.get("matha"));
        return ;
    }
}
