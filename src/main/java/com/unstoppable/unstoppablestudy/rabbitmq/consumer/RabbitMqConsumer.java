package com.unstoppable.unstoppablestudy.rabbitmq.consumer;

import com.unstoppable.unstoppablestudy.cache.StudyLocalCache;
import com.unstoppable.unstoppablestudy.rabbitmq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class RabbitMqConsumer {


    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.COM_UNSTOPPABLE_STUDY_QUEUE)
    private void studyMessage(Message message){
     String msg=new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("收到消息：{}",msg);
        messageHandler(msg);

        StudyLocalCache.STRING_COUNT_CACHE.forEach((k,v)->{
            log.info("key:{},value:{}",k,v);
        });
    }

    protected void messageHandler(String msg){
        StudyLocalCache.STRING_COUNT_CACHE.computeIfAbsent(msg, k->1);
        StudyLocalCache.STRING_COUNT_CACHE.computeIfPresent(msg,(k,v)-> v+1);
    }
}
