package com.unstoppable.unstoppablestudy.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.unstoppable.unstoppablestudy.cache.StudyLocalCache;
import com.unstoppable.unstoppablestudy.rabbitmq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class RabbitMqConsumer {


    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.COM_UNSTOPPABLE_FIRST_STUDY_QUEUE)
    private void firstStudyMessage(Message message,Channel channel) throws IOException {
     String msg=new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("队列first，收到消息：{}",msg);
        messageHandler(msg);

        StudyLocalCache.STRING_COUNT_CACHE.forEach((k,v)->{
            log.info("key:{},value:{}",k,v);
        });
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }

    protected void messageHandler(String msg){
        StudyLocalCache.STRING_COUNT_CACHE.compute(msg,(k,v)->v==null?1:v+1);
    }
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.COM_UNSTOPPABLE_SECOND_STUDY_QUEUE)
    protected void  secondStudyMessage(Message message, Channel channel) throws IOException {
    String msg=new String(message.getBody(),StandardCharsets.UTF_8);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("队列2:，收到消息：{}",msg);
    if (message.equals("1")){
        log.info("拒签1");
        channel.basicNack(deliveryTag,true,true);
        return;
    }
    channel.basicAck(deliveryTag,true);
    }
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.COM_UNSTOPPABLE_ALL_STUDY_QUEUE)
    protected void  allStudyMessage(Message message, Channel channel) throws IOException {
       String msg=this.getMessage(message);
       log.info("所有队列，哈哈，我监听到你的消息了--->{},0.0",msg);
       channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }


    private String getMessage(Message message){
        return  new String(message.getBody(),StandardCharsets.UTF_8);
    }
}
