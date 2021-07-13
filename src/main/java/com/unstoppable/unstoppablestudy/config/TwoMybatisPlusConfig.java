package com.unstoppable.unstoppablestudy.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = {"com.unstoppable.unstoppablestudy.dao.two"}, sqlSessionFactoryRef = "twoSqlSessionFactory")
public class TwoMybatisPlusConfig {
    @Bean("twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.two")
    public DataSource biDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory twoSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(biDataSource());
        bean.setVfs(SpringBootVFS.class);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/two/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager twoTransactionManager() {
        return new DataSourceTransactionManager(biDataSource());
    }

    @Bean
    public SqlSessionTemplate biSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(twoSqlSessionFactory());
    }

}
