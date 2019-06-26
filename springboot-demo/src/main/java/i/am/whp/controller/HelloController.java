package i.am.whp.controller;

import com.alibaba.fastjson.JSON;
import i.am.whp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    MyService myService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/hi")
    @ResponseBody
    public String hello() {
//        ModelAndView modelAndView = new ModelAndView();
        return JSON.toJSONString(myService.hi());
    }

}