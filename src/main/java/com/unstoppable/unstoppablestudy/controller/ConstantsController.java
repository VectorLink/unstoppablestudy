package com.unstoppable.unstoppablestudy.controller;

import com.unstoppable.unstoppablestudy.UnstoppablestudyApplication;
import com.unstoppable.unstoppablestudy.config.ExternalPropertiesRefresh;
import com.unstoppable.unstoppablestudy.constants.ExternalProperties;
import com.unstoppable.unstoppablestudy.constants.FileProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

@RestController
@Api(tags = "常量控制器")
@RefreshScope
public class ConstantsController {
    @Value("${test.name}")
    private String testName;
    @Value("${test2.name}")
    private String test2Name;
    @Resource
    ContextRefresher contextRefresher;

    @Resource
    ExternalProperties externalProperties;
    @Autowired
    ExternalPropertiesRefresh externalPropertiesRefresh;


    @Resource
    FileProperties fileProperties;
    @ApiOperation("更新常量")
    @RequestMapping(method = RequestMethod.GET,value = "/resfreshConstants")
    public String freshConfig() throws FileNotFoundException {
        String url="D://test.properties";
        final String key="test.name";
        Properties props = new Properties();
        FileInputStream fileInputStream=new FileInputStream(url);
        try {
            props.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Field[] fields = FileProperties.class.getFields();

        String name = props.getProperty(key);
//        fileProperties.setName(name);
        externalPropertiesRefresh.refresh();
        contextRefresher.refreshEnvironment();
        UnstoppablestudyApplication.getStandardEnvironment().getPropertySources().addLast(new PropertiesPropertySource("system", props));
        return name;

    }

    @ApiOperation("获取常量")
    @RequestMapping(method = RequestMethod.GET,value = "/getConstatns")
    public String getConstants() throws FileNotFoundException {
        String name = fileProperties.getName();
        return name;

    }

    @ApiOperation("获取常量2")
    @RequestMapping(method = RequestMethod.GET,value = "/getConstatns2")
    public String getConstants2() throws FileNotFoundException {
        return testName;

    }

    @ApiOperation("获取常量3")
    @RequestMapping(method = RequestMethod.GET,value = "/getConstatns3")
    public String getConstants3() throws FileNotFoundException {
        return test2Name;

    }

    @ApiOperation("获取常量4")
    @RequestMapping(method = RequestMethod.GET,value = "/getConstatns4")
    public String getConstants4() throws FileNotFoundException {
        return externalProperties.getName();

    }

}
