package com.unstoppable.unstoppablestudy.rabbitmq.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unstoppable.unstoppablestudy.rabbitmq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
@Slf4j
public class RabbitProducerService implements InitializingBean {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(Object object) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    send(object);
                }
            });
        } else {
            send(object);
        }

    }

    public void send(Object object) {
        if (object instanceof String || object instanceof JSONObject) {
            rabbitTemplate.convertAndSend(RabbitConfig.COM_UNSTOPPABLE_STUDY_TOPIC,RabbitConfig.COM_UNSTOPPABLE_STUDY_ROUTE_KEY,object);
        } else {
            rabbitTemplate.convertAndSend(RabbitConfig.COM_UNSTOPPABLE_STUDY_TOPIC,RabbitConfig.COM_UNSTOPPABLE_STUDY_ROUTE_KEY, JSON.toJSONString(object));
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
