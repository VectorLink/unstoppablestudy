package com.unstoppable.unstoppablestudy.rabbitmq.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unstoppable.unstoppablestudy.constants.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class RabbitProducerService implements InitializingBean {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String topic, String routeKey, Object object, MessagePostProcessor postProcessor) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    send(topic,routeKey,object,postProcessor);
                }
            });
        } else {
            send(topic,routeKey,object,postProcessor);
        }

    }

    private void send(String topic,String routeKey,Object object,MessagePostProcessor postProcessor) {
        if (object instanceof String ) {
            if (Objects.nonNull(postProcessor)){
                rabbitTemplate.convertAndSend(topic,routeKey,object,postProcessor);
            }else {
                rabbitTemplate.convertAndSend(topic,routeKey,object);
            }

        } else {
            if (Objects.nonNull(postProcessor)){
                rabbitTemplate.convertAndSend(topic,routeKey,JSON.toJSONString(object),postProcessor);
            }else {
                rabbitTemplate.convertAndSend(topic,routeKey,JSON.toJSONString(object));
            }
        }
    }
    //可靠性投递
    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack){
                log.info("消息投递成功");
            }else {
                log.error("消息投递失败，{}",cause);
            }
        });
        rabbitTemplate.setReturnsCallback(returned -> {
            log.error("exchange 投递到queue失败------》0.0");
            log.error("错误信息，{}",returned);
        });
    }
}
