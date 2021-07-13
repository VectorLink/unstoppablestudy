package com.unstoppable.unstoppablestudy.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
@MapperScan(value = {"com.unstoppable.unstoppablestudy.dao.one"}, sqlSessionFactoryRef = "oneSqlSessionFactory")
public class OneMybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean("oneDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.one")
    public DataSource oneDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory oneSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        Interceptor[] plugins = { paginationInterceptor(), optimisticLockerInterceptor() }; //设置分页,乐观锁插件
        bean.setPlugins(plugins);
        bean.setVfs(SpringBootVFS.class);
        bean.setDataSource(oneDataSource());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/one/*.xml"));
        //全局配置
        GlobalConfig globalConfig  = new GlobalConfig();
        bean.setGlobalConfig(globalConfig);
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager pmsTransactionManager() {
        return new DataSourceTransactionManager(oneDataSource());
    }

    @Bean
    @Primary
    public SqlSessionTemplate pmsSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(oneSqlSessionFactory());
    }
}
