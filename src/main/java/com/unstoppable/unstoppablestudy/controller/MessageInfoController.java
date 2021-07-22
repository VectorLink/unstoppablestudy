package com.unstoppable.unstoppablestudy.controller;

import com.unstoppable.unstoppablestudy.service.PopulateSiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@Api(tags = "测试方法用API")
public class MessageInfoController {
    @Resource
    private PopulateSiteService populateSiteService;
    @ApiOperation(value = "测试方法")
    @RequestMapping(value = "testMethod",method = RequestMethod.POST)
    public void sendRabbitMsg(@RequestBody String msg){
        populateSiteService.TestTransaction();
    }
}
