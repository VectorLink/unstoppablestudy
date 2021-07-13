package com.unstoppable.unstoppablestudy;

import com.unstoppable.unstoppablestudy.generate.MysqlGenerator;
import org.junit.Test;

public class MysqlGeneratorTest {

    @Test
    public void testOneGenerator(){
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        mysqlGenerator.setXml(true);
//        mysqlGenerator.setController(true);
        mysqlGenerator.generator("one", "one","populate_site");
    }

    @Test
    public void testTwoGenerator(){
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        mysqlGenerator.setXml(true);
        mysqlGenerator.generator("two", "two", "user_info");
    }
//    @Test
//    public void testGenerator4PmsSlave(){
//        //从库查询用
//        MysqlGenerator mysqlGenerator = new MysqlGenerator();
//        mysqlGenerator.setXml(true);
////        mysqlGenerator.setController(true);
//        mysqlGenerator.generator("pms-slave", "pmsslave","v3_inner_procurement_order_line");
//    }
}
