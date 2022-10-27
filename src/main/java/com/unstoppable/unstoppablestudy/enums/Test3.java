package com.unstoppable.unstoppablestudy.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test3 {
    public static void main(String[] args) {
        List<String> a= Stream.of("200").collect(Collectors.toList());
        System.out.println("1:"+System.identityHashCode(a));
        changeValue(a);
        System.out.println("4:"+System.identityHashCode(a));
        System.out.println(a);
    }
    private static void changeValue(List<String> a){
        System.out.println("2:"+System.identityHashCode(a));
        System.out.println(a);
        List<String> b = Stream.of("500").collect(Collectors.toList());
        a=b;
        System.out.println(a);
        System.out.println("3:"+System.identityHashCode(a));
    }
}
