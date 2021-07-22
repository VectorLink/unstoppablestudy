package com.unstoppable.unstoppablestudy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unstoppable.unstoppablestudy.rabbitmq.producer.RabbitProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "消息队列")
@Slf4j
public class RabbitMqController {



    @Resource
    private RabbitProducerService rabbitProducerService;
    @ApiOperation(value = "消息队列-发送字符串")
    @RequestMapping(value = "sendMsg",method = RequestMethod.POST)
    public void sendRabbitMsg(@RequestBody  String msg){
        if (StringUtils.isNoneBlank(msg)){
            rabbitProducerService.sendMsg(msg);
        }
        log.info("完成消息发送：{}",msg);
    }

    @ApiOperation("消息队列-发送JSON")
    @RequestMapping(value = "sendJson",method = RequestMethod.POST)
    public void sendJson(@RequestBody String json){
        JSONObject jsonObject=JSON.parseObject(json);
        rabbitProducerService.send(jsonObject);

    }
}
