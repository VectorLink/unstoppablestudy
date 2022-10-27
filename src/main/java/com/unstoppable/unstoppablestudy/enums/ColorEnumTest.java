package com.unstoppable.unstoppablestudy.enums;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;

@Log4j2
public class ColorEnumTest {

    public static void main(String[] args) {
            int redCode = ColorEnum.RED.getCode();

       ColorEnum colorEnum =ColorEnum.WHITE;
        log.info("coler 内存地址 :{}",System.identityHashCode(colorEnum));
        log.info(colorEnum);
            setColor(redCode,colorEnum);
        log.info("coler 内存地址2 :{}",System.identityHashCode(colorEnum));
        log.info("coler:{}",colorEnum);
//       ColorEnum.getByCode(redCode,colorEnum);
//        log.info("coler 内存地址3 :{}",System.identityHashCode(colorEnum));
//        log.info("coler:{}",colorEnum);

//        Integer b=20090;
//        ColorEnumTest  colorEnumTest = new ColorEnumTest();
//        log.info("=={}",colorEnumTest.equals(b,redCode));
//        log.info("=={}",colorEnumTest.equals2(b,redCode));
//        Assert.isTrue(colorEnumTest.equals(b,redCode),"不相等");
//        Assert.isTrue(colorEnumTest.equals2(b,redCode),"不相等");

    }

    static void setColor(int redCode,ColorEnum colorEnum){
        log.info("colorEnum 内存地址3 :{}",System.identityHashCode(colorEnum));
        log.info("===");
        log.info(colorEnum);
        log.info("===");
        if(ColorEnum.RED.getCode()==(redCode)){
            colorEnum= ColorEnum.RED;
            log.info("===");
            log.info(colorEnum);
            log.info("===");

            log.info("colorEnum 内存地址 4:{}",System.identityHashCode(colorEnum));
        }

    }

     boolean equals(Integer bi, int b){
        return bi == 20090;
     }

    boolean equals2(Integer bi, int b){
        return bi == 20090;
    }
}
