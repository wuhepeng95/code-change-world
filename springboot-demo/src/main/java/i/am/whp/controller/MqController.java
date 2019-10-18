package i.am.whp.controller;

import i.am.whp.model.MyTable;
import i.am.whp.service.MyService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("demo")
@Profile("with_rabbitmq")
public class MqController {

    @Autowired
    MyService myService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Resource(name = "myMessageConverter")
    MessageConverter messageConverter;

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
