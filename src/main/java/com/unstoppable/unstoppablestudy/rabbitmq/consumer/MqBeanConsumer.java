package com.unstoppable.unstoppablestudy.rabbitmq.consumer;

import com.unstoppable.unstoppablestudy.constants.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@ConditionalOnExpression(value = "${mq.disable}")
@Slf4j
public class MqBeanConsumer {

    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.COM_UNSTOPPABLE_FIRST_STUDY_QUEUE)
    public void consumer(Message _msg){
    String message= new String( _msg.getBody(), StandardCharsets.UTF_8);
    log.info("message:{}",message);
    log.info("配置信息：{}",System.getProperties().getProperty("mq.disable"));
    }
}
