package com.unstoppable.unstoppablestudy.constants;

/**
 * 定义常量
 */
public class RabbitConstants {
    /**
     * rabbit mq 过期时间 值， 毫秒
     */
    public static final Integer RABBIT_MQ_TTL_VALUE=10000;
    public static final String COM_UNSTOPPABLE_STUDY_TOPIC="com_unstoppable_study_topic";

    public static final String COM_UNSTOPPABLE_STUDY_ROUTE_KEY="route.#";
    public static final String COM_UNSTOPPABLE_ALL_STUDY_QUEUE="com_unstoppable_all_study_queue";

    public static final String COM_UNSTOPPABLE_SECOND_STUDY_QUEUE="com_unstoppable_second_study_queue";
    public static final String COM_UNSTOPPABLE_SECOND_STUDY_ROUTE_KEY="route.second";

    public static final String COM_UNSTOPPABLE_FIRST_STUDY_QUEUE="com_unstoppable_first_study_queue";
    public static final String COM_UNSTOPPABLE_FIRST_STUDY_ROUTE_KEY="route.first";

    public static final String COM_UNSTOPPABLE_STUDY_TTL_QUEUE="com_unstoppable_study_ttl_queue";
    public static final String COM_UNSTOPPABLE_STUDY_TTL_ROUTE_KEY="route.ttl";

    /**
     * 死信队列
     */
    public static final String COM_UNSTOPPABLE_STUDY_DLX_QUEUE="com_unstoppable_study_dlx_queue";
    public static final String COM_UNSTOPPABLE_STUDY_DLX_ROUTE_KEY="route.dlx";
    public static final String COM_UNSTOPPABLE_STUDY_DLX_TOPIC="com_unstoppable_study_dlx_topic";
}
