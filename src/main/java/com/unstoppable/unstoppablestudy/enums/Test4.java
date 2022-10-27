package com.unstoppable.unstoppablestudy.enums;

import com.unstoppable.unstoppablestudy.dao.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Slf4j
public class Test4 {
    public static void main(String[] args) {
        UserInfo a = UserInfo.builder().id(200).build();
        log.info("{}",a);
        System.out.println("1:"+System.identityHashCode(a));
        System.out.println("11:"+System.identityHashCode(a.getId()));
        a = changeValue(a);
        System.out.println(a.hashCode());
        System.out.println("4:"+System.identityHashCode(a));
        System.out.println("41:"+System.identityHashCode(a.getId()));
        System.out.println(a);
////        changeValue2(a);
//        System.out.println("5:"+System.identityHashCode(a));
//        System.out.println("51:"+System.identityHashCode(a.getId()));
//        System.out.println(a);
    }
    private static UserInfo changeValue(UserInfo a){
        log.info("{}",a);
        System.out.println(a.hashCode());
        System.out.println("2:"+System.identityHashCode(a));
        System.out.println(a);
        a= UserInfo.builder().id(5).build();
        System.out.println(a);
        System.out.println("3:"+System.identityHashCode(a));
        return a;
    }

//    private static void changeValue2(UserInfo a){
//        System.out.println("2:"+System.identityHashCode(a));
//        System.out.println("21:"+System.identityHashCode(a.getId()));
//        System.out.println(a);
//        a.setId(2);
//        System.out.println(a);
//        System.out.println("3:"+System.identityHashCode(a));
//        System.out.println("31:"+System.identityHashCode(a.getId()));
//    }

}
