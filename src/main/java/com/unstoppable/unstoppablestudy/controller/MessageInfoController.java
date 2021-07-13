package com.unstoppable.unstoppablestudy.controller;

import com.unstoppable.unstoppablestudy.service.PopulateSiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class MessageInfoController {
    @Resource
    private PopulateSiteService populateSiteService;

    @RequestMapping(value = "testMethod",method = RequestMethod.POST)
    public void sendRabbitMsg(@RequestBody String msg){
        populateSiteService.TestTransaction();
    }
}
