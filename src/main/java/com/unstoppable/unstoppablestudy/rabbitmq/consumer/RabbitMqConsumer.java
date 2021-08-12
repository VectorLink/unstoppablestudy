package com.unstoppable.unstoppablestudy.rabbitmq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.unstoppable.unstoppablestudy.cache.StudyLocalCache;
import com.unstoppable.unstoppablestudy.config.RabbitConfig;
import com.unstoppable.unstoppablestudy.constants.RabbitConstants;
import com.unstoppable.unstoppablestudy.dao.entity.UserInfo;
import com.unstoppable.unstoppablestudy.service.MyFunctionInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
@Slf4j
public class RabbitMqConsumer {
    @Resource
    private ObjectMapper objectMapper;

    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.COM_UNSTOPPABLE_FIRST_STUDY_QUEUE)
    private void firstStudyMessage(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("队列first，收到消息：{}", msg);
        messageHandler(msg);

        StudyLocalCache.STRING_COUNT_CACHE.forEach((k, v) -> {
            log.info("key:{},value:{}", k, v);
        });
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    protected void messageHandler(String msg) {
        StudyLocalCache.STRING_COUNT_CACHE.compute(msg, (k, v) -> v == null ? 1 : v + 1);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.COM_UNSTOPPABLE_SECOND_STUDY_QUEUE)
    protected void secondStudyMessage(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("队列2:，收到消息：{}", msg);
        JsonNode jsonNode = objectMapper.readTree(msg).findValue("json");
        if (Objects.nonNull(jsonNode) && jsonNode.get("json").equals(1)) {
            log.info("拒签1");
            channel.basicNack(deliveryTag, true, false);
            return;
        } else {
            UserInfo u = objectMapper.readValue(msg, UserInfo.class);
            this.consumer.accept(u);
            Boolean bolean=function.apply(u);
            if (bolean){
                log.info("确实是大于30岁");
            }
            BigDecimal value= binaryOperator.apply(BigDecimal.valueOf(u.getAge()),BigDecimal.ONE);
            log.info("年龄加了1，{}",value);

            log.info("开始执行自定义函数");
           BigDecimal result= myFunctionInterface.apply(u.getSalary(),u.getAge(),u.getId().doubleValue(),u.getAge().longValue());
           log.info("自定义函数执行结果：{}",result);

           log.info("用户是否大于30？，{}",predicate.test(u));
           //大于30岁并且小于40岁
           predicate.and(predicate2).test(u);


        }
        channel.basicAck(deliveryTag, true);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.COM_UNSTOPPABLE_ALL_STUDY_QUEUE)
    protected void allStudyMessage(Message message, Channel channel) throws IOException {
        String msg = this.getMessage(message);
        log.info("所有队列，哈哈，我监听到你的消息了--->{},0.0", msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.COM_UNSTOPPABLE_STUDY_DLX_QUEUE)
    protected void studyRabbitDlxMessage(Message message, Channel channel) throws IOException {
        String msg = this.getMessage(message);
        log.info("===========================接受到死信队列消息====================");
        log.info("死信队列消息：{}", msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }


    private String getMessage(Message message) {
        return new String(message.getBody(), StandardCharsets.UTF_8);
    }

    Consumer<UserInfo> consumer = (t) -> log.info("收到了学生的信息", t.getAge());

    Predicate<UserInfo> predicate=(t)-> t.getAge()>30;
    Predicate<UserInfo> predicate2=t->t.getAge()<40;

    Function<UserInfo,Boolean> function=(t)->{
      if (t.getAge()>30){
          return Boolean.TRUE;
      }else {
          return Boolean.FALSE;
      }
    };

    BinaryOperator<BigDecimal> binaryOperator=(n1, n2)->{
        BigDecimal result= n1.add(n2);
        return result;
    };

    MyFunctionInterface<BigDecimal,Integer,Double,Long,BigDecimal> myFunctionInterface=(t,u,h,e)->{
       BigDecimal p=t.add(BigDecimal.valueOf(u)).add(BigDecimal.valueOf(h)).add(BigDecimal.valueOf(e));
        return p;
    };
}
