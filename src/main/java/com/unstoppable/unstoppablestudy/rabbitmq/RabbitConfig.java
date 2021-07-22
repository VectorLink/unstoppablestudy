package com.unstoppable.unstoppablestudy.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String COM_UNSTOPPABLE_STUDY_TOPIC="com_unstoppable_study_topic";
    public static final String COM_UNSTOPPABLE_STUDY_ROUTE_KEY="route.#";
    public static final String COM_UNSTOPPABLE_ALL_STUDY_QUEUE="com_unstoppable_all_study_queue";

    public static final String COM_UNSTOPPABLE_SECOND_STUDY_QUEUE="com_unstoppable_second_study_queue";
    public static final String COM_UNSTOPPABLE_SECOND_STUDY_ROUTE_KEY="route.second";

    public static final String COM_UNSTOPPABLE_FIRST_STUDY_QUEUE="com_unstoppable_first_study_queue";
    public static final String COM_UNSTOPPABLE_FIRST_STUDY_ROUTE_KEY="route.first";

    @Bean(name="studyFirstQueue")
    public Queue studyFirstQueue(){
        return new Queue(COM_UNSTOPPABLE_FIRST_STUDY_QUEUE);
    }
    @Bean(name = "studyTopicExchange")
    public TopicExchange studyTopicExchange(){
        return new TopicExchange(COM_UNSTOPPABLE_STUDY_TOPIC);
    }
    @Bean
    public Binding studyBinding(@Qualifier("studyFirstQueue") Queue queue, @Qualifier("studyTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(COM_UNSTOPPABLE_FIRST_STUDY_ROUTE_KEY);
    }
    @Bean("secondStudyQueue")
    public Queue secondStudyQueue(){
        return QueueBuilder.durable(COM_UNSTOPPABLE_SECOND_STUDY_QUEUE).build();
    }

    @Bean
    public Binding studySecondBinding(@Qualifier("secondStudyQueue") Queue queue, @Qualifier("studyTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(COM_UNSTOPPABLE_SECOND_STUDY_ROUTE_KEY);
    }
    @Bean("allStudyQueue")
    public Queue allStudyQueue(){
        return QueueBuilder.durable(COM_UNSTOPPABLE_ALL_STUDY_QUEUE).build();
    }
    @Bean
    public Binding allSecondBinding(@Qualifier("allStudyQueue") Queue queue, @Qualifier("studyTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(COM_UNSTOPPABLE_STUDY_ROUTE_KEY);
    }
}
