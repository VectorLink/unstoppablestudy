package com.unstoppable.unstoppablestudy.enums;

public class Test2 {
    public static void main(String[] args) {
         Integer a=200;
        System.out.println("1:"+System.identityHashCode(a));
        changeValue(a);
        System.out.println("4:"+System.identityHashCode(a));
        System.out.println(a);
    }
    private static void changeValue(Integer a){
        System.out.println("2:"+System.identityHashCode(a));
        System.out.println(a);
        a=new Integer(300) ;
        System.out.println(a);
        System.out.println("3:"+System.identityHashCode(a));
    }
}
