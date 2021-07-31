package com.unstoppable.unstoppablestudy.config;

import com.unstoppable.unstoppablestudy.constants.RabbitConstants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.unstoppable.unstoppablestudy.constants.RabbitConstants.*;

@Configuration
public class RabbitConfig {

    @Bean(name="studyFirstQueue")
    public Queue studyFirstQueue(){
        return QueueBuilder.durable(COM_UNSTOPPABLE_FIRST_STUDY_QUEUE)
                .deadLetterExchange(COM_UNSTOPPABLE_STUDY_DLX_TOPIC)
                .deadLetterRoutingKey(COM_UNSTOPPABLE_STUDY_DLX_ROUTE_KEY)
                .build();
    }
    @Bean(name = "studyTopicExchange")
    public TopicExchange studyTopicExchange(){
        return new TopicExchange(COM_UNSTOPPABLE_STUDY_TOPIC);
    }
    @Bean
    public Binding studyBinding(@Qualifier("studyFirstQueue") Queue queue, @Qualifier("studyTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConstants.COM_UNSTOPPABLE_FIRST_STUDY_ROUTE_KEY);
    }
    @Bean("secondStudyQueue")
    public Queue secondStudyQueue(){
        return QueueBuilder.durable(RabbitConstants.COM_UNSTOPPABLE_SECOND_STUDY_QUEUE)
                .deadLetterExchange(COM_UNSTOPPABLE_STUDY_DLX_TOPIC)
                .deadLetterRoutingKey(COM_UNSTOPPABLE_STUDY_DLX_ROUTE_KEY)
                .build();
    }

    @Bean
    public Binding studySecondBinding(@Qualifier("secondStudyQueue") Queue queue, @Qualifier("studyTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConstants.COM_UNSTOPPABLE_SECOND_STUDY_ROUTE_KEY);
    }
    @Bean("allStudyQueue")
    public Queue allStudyQueue(){
        return QueueBuilder.durable(COM_UNSTOPPABLE_ALL_STUDY_QUEUE)
                .deadLetterExchange(COM_UNSTOPPABLE_STUDY_DLX_TOPIC)
                .deadLetterRoutingKey(COM_UNSTOPPABLE_STUDY_DLX_ROUTE_KEY)
                .build();
    }
    @Bean
    public Binding allSecondBinding(@Qualifier("allStudyQueue") Queue queue, @Qualifier("studyTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(COM_UNSTOPPABLE_STUDY_ROUTE_KEY);
    }
//    TTL队列
    @Bean
    public Queue studyTTLQueue(){
        return QueueBuilder.durable(COM_UNSTOPPABLE_STUDY_TTL_QUEUE).ttl(RABBIT_MQ_TTL_VALUE)
                .deadLetterExchange(COM_UNSTOPPABLE_STUDY_DLX_TOPIC)
                .deadLetterRoutingKey(COM_UNSTOPPABLE_STUDY_DLX_ROUTE_KEY)
                .build();
    }
    @Bean
    public Binding studyTtlBinding(@Qualifier("studyTTLQueue") Queue queue,@Qualifier("studyTopicExchange") TopicExchange exchange ){
        return  BindingBuilder.bind(studyTTLQueue()).to(exchange).with(COM_UNSTOPPABLE_STUDY_TTL_ROUTE_KEY);
    }
    /**
     * 死信队列
     * 1。超长： 队列长度达到限制
     * 2。拒收： 消费消息是拒绝消费，并且不回原队列
     * 3。超时： 队列设置的有超时时间
     */
    @Bean
    public Queue studyDlxQueue(){
        return QueueBuilder.durable(COM_UNSTOPPABLE_STUDY_DLX_QUEUE).ttl(RABBIT_MQ_TTL_VALUE).build();
    }
    @Bean
    public  TopicExchange studyDlxExchange(){
        return new TopicExchange(COM_UNSTOPPABLE_STUDY_DLX_TOPIC);
    }
    @Bean
    public Binding studyDlxBinding(@Qualifier("studyDlxQueue") Queue queue,@Qualifier("studyDlxExchange") TopicExchange exchange){
        return  BindingBuilder.bind(queue).to(exchange).with(COM_UNSTOPPABLE_STUDY_DLX_ROUTE_KEY);
    }
}
