package com.example.demo.controller;

import com.example.demo.service.AsyncService;
import com.example.demo.service.NestedService;
import com.example.demo.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class TestMybatisController {

    public TestMybatisController(List<NestedService> nestedServiceList) {
        System.out.println(nestedServiceList);
    }

    @Autowired
    private TestServiceImpl testServiceImpl;
    @Autowired
    private NestedService nestedService;

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/testRollback")
    public String testRollback() {

        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(10) * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------start-----------------------");
//        testServiceImpl.rollBackMethod("test");
        testServiceImpl.retryTest();
        System.out.println("-----------------------------end-----------------------");
        return "success";
    }

    @RequestMapping("/testNested")
    public String testNested() {
        nestedService.insertUser();
        return "success";
    }

    @RequestMapping("/testAsync")
    public String testAsync() {
        asyncService.testAsync();
        return "success";
    }
}
