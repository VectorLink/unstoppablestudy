package com.unstoppable.unstoppablestudy.rabbitmq.producer;

import com.alibaba.fastjson.JSON;
import com.unstoppable.unstoppablestudy.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

@Service
public class RabbitProducerService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(Object object){
        if (TransactionSynchronizationManager.isSynchronizationActive()){
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    send(object);
                }
            });
        }else {
            send(object);
        }

    }
    public void send(Object object){
        if (object instanceof String){
            rabbitTemplate.convertAndSend(RabbitConfig.COM_UNSTOPPABLE_STUDY_QUEUE,object);
        }else {
            rabbitTemplate.convertAndSend(RabbitConfig.COM_UNSTOPPABLE_STUDY_QUEUE, JSON.toJSONString(object));
        }
    }
}
