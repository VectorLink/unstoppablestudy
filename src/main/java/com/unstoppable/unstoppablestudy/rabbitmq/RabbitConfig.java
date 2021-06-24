package com.unstoppable.unstoppablestudy.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String COM_UNSTOPPABLE_STUDY_TOPIC="com_unstoppable_study_topic";
    public static final String COM_UNSTOPPABLE_STUDY_QUEUE="com_unstoppable_study_queue";
    public static final String COM_UNSTOPPABLE_STUDY_ROUTE_KEY="com_unstoppable_study_route.*";

    @Bean(name="studyQueue")
    public Queue studyQueue(){
        return new Queue(COM_UNSTOPPABLE_STUDY_QUEUE);
    }
    @Bean(name = "studyTopicExchange")
    public TopicExchange studyTopicExchange(){
        return new TopicExchange(COM_UNSTOPPABLE_STUDY_TOPIC);
    }

    public Binding studyBinding(@Qualifier("studyQueue") Queue queue, @Qualifier("studyTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(COM_UNSTOPPABLE_STUDY_ROUTE_KEY);
    }
}
