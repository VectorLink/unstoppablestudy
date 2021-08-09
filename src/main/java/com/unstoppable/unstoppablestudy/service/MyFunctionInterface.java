package com.unstoppable.unstoppablestudy.service;

/**
 * 自定义函数接口
 * @param <T>
 * @param <U>
 * @param <E>
 * @param <H>
 * @param <R>
 */
@FunctionalInterface
public interface MyFunctionInterface<T,U,E,H,R> {
    /**
     * 执行
     * @param t
     * @param u
     * @param e
     * @param h
     * @return
     */
    R apply(T t,U u,E e,H h);
}
