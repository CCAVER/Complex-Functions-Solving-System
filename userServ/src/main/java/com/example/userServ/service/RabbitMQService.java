package com.example.userServ.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface RabbitMQService {
    String sendMsg(String msg) throws Exception;

}
