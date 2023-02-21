package com.siyu.messervice.controller;

import annotation.mq.DirectExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/direct")
public class DirectController {
    @Resource
    private final RabbitTemplate rabbitTemplate;

    public DirectController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @GetMapping("/send")
    public Object sendMsg() {
//        for (int i = 0; i < 10; i++) {
//            rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_ROUTING_KEY, "发送一条测试消息："+i);
//        }
        String messageA = "server message sendToClient";
        String messageB = "server message sendToClient Alll People reviced";
        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend("myQueue",messageA+": "+i);
        }
        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend("my-fanoutRoute","",messageB+": "+i);
        }
        return "消息发送成功！！";
    }
}
