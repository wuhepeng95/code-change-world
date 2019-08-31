package i.am.whp.controller;

import com.alibaba.fastjson.JSON;
import i.am.whp.model.MyTable;
import i.am.whp.service.MyService;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    MyService myService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Resource(name = "myMessageConverter")
    MessageConverter messageConverter;

    @RequestMapping("/test")
    @ResponseBody
    public String hello() {
//        ModelAndView modelAndView = new ModelAndView();
        return JSON.toJSONString(myService.hi());
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List getData(@RequestParam("keyword") String keyword) {
        System.out.println("controller:" + MDC.get("guid"));
        return myService.getData(keyword);
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage() {
        rabbitTemplate.setExchange("whp.test.exchange");
        rabbitTemplate.setRoutingKey("whp.test.route.key");
        rabbitTemplate.setMessageConverter(messageConverter);

        // message
        MyTable message = new MyTable();
        message.setId(2);
        message.setName("name");
        message.setCreateTime(new Date());

        rabbitTemplate.convertAndSend(message);
        return "send success";
    }


}
