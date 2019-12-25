package i.am.whp.controller;

import i.am.whp.config.mq.DirectRabbitConfig;
import i.am.whp.config.mq.TopicRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/rabbitmq")
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "send direct message.";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> message = new HashMap<>(4);
        message.put("messageId", messageId);
        message.put("messageData", messageData);
        message.put("createTime", createTime);
        rabbitTemplate.convertAndSend(DirectRabbitConfig.DIRECT_EXCHANGE, DirectRabbitConfig.ROUTING_KEY, message);
        return "ok";
    }

    @GetMapping("/sendTopicMessage")
    public String sendTopicMessage(@RequestParam String routingKey) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "routingKey:" + routingKey;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> message = new HashMap<>();
        message.put("messageId", messageId);
        message.put("messageData", messageData);
        message.put("createTime", createTime);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE, routingKey, message);
        return "ok";
    }

}
