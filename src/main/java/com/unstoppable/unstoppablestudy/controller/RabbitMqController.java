package com.unstoppable.unstoppablestudy.controller;

import com.unstoppable.unstoppablestudy.rabbitmq.producer.RabbitProducerService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class RabbitMqController {
    @Resource
    private RabbitProducerService rabbitProducerService;

    @RequestMapping(value = "sendMsg",method = RequestMethod.POST)
    public void sendRabbitMsg(@RequestBody String msg){
        log.info("收到消息：{}",msg);
        if (StringUtils.isNoneBlank(msg)){
            rabbitProducerService.sendMsg(msg);
        }
        log.info("完成消息发送：{}",msg);
    }
}