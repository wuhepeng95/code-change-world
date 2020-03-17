package i.am.whp.controller;

import com.alibaba.fastjson.JSONObject;
import i.am.whp.config.kafka.KafkaSender;
import i.am.whp.config.rabbitmq.DirectRabbitConfig;
import i.am.whp.config.rabbitmq.TopicRabbitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("demo")
@Profile({"with_rabbitmq", "with_kafka"})
public class MqController {

//    @Autowired
//    RabbitTemplate rabbitTemplate;
//    @Resource(name = "myMessageConverter")
//    MessageConverter messageConverter;
//
//    @RequestMapping("/sendMessage")
//    @ResponseBody
//    public String sendMessage() {
//        rabbitTemplate.setExchange("whp.test.exchange");
//        rabbitTemplate.setRoutingKey("whp.test.route.key");
//        rabbitTemplate.setMessageConverter(messageConverter);
//
//        // message
//        MyTable message = new MyTable();
//        message.setId(2);
//        message.setName("name");
//        message.setCreateTime(new Date());
//
//        rabbitTemplate.convertAndSend(message);
//        return "send success";
//    }
//        @GetMapping("/sendDirectMessage")
//        public String sendDirectMessage() {
//            String messageId = String.valueOf(UUID.randomUUID());
//            String messageData = "send direct message.";
//            String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            Map<String, Object> message = new HashMap<>(4);
//            message.put("messageId", messageId);
//            message.put("messageData", messageData);
//            message.put("createTime", createTime);
//            rabbitTemplate.convertAndSend(DirectRabbitConfig.DIRECT_EXCHANGE, DirectRabbitConfig.ROUTING_KEY, message);
//            return "ok";
//        }
//
//    @GetMapping("/sendTopicMessage")
//    public String sendTopicMessage(@RequestParam String routingKey) {
//        String messageId = String.valueOf(UUID.randomUUID());
//        String messageData = "routingKey:" + routingKey;
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Map<String, Object> message = new HashMap<>();
//        message.put("messageId", messageId);
//        message.put("messageData", messageData);
//        message.put("createTime", createTime);
//        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE, routingKey, message);
//        return "ok";
//    }

    @Autowired
    KafkaSender kafkaSender;

    @RequestMapping("/sendMessageToKafka")
    @ResponseBody
    public String sendMessageToKafka() {
        Map<String, String> messageMap = new HashMap();
        messageMap.put("message", "我是一条消息");
        String taskid = "123456";
        String jsonStr = JSONObject.toJSONString(messageMap);
        //kakfa的推送消息方法有多种，可以采取带有任务key的，也可以采取不带有的（不带时默认为null）
        kafkaSender.send("maxwell", taskid, jsonStr);
        return "send success";
    }

   
}
