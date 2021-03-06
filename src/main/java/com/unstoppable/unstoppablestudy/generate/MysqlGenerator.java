package com.unstoppable.unstoppablestudy.generate;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * <p>
 * Mysql代码生成器
 * </p>
 *
 * @author Caratacus
 */
public class MysqlGenerator extends SuperGenerator {

    /**
     * <p>
     * MySQL generator
     * </p>
     */
    public void generator(String db, String path, String... tableName) {
        this.db = db;
        this.path = path;
        // 代码生成器
        AutoGenerator mpg = getAutoGenerator(tableName);
        mpg.execute();
        if (tableName == null) {
            System.err.println(" Generator Success !");
        } else {

            System.err.println(" TableName【 " + tableName + " 】" + "Generator Success !");

        }
    }
}
