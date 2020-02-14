package i.am.whp.listener.rabbitmq;

import i.am.whp.config.mq.TopicRabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Profile("with_rabbitmq")
public class TopicReceiver {

    private static Logger logger = LoggerFactory.getLogger(TopicReceiver.class);

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(TopicRabbitConfig.TOPIC_QUEUE_ONE),
            exchange = @Exchange(value = TopicRabbitConfig.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = TopicRabbitConfig.TOPIC_ROUTING_KEY_ONE
    ))
    public void topicOnMessage1(Map message) {
        logger.info("TopicListener1 received.message:{}", message.toString());
    }

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(TopicRabbitConfig.TOPIC_QUEUE_ONE),
            exchange = @Exchange(value = TopicRabbitConfig.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = TopicRabbitConfig.TOPIC_ROUTING_KEY_ONE
    ))
    public void topicOnMessage3(Map message) {
        logger.info("TopicListener3 received.message:{}", message.toString());
    }

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(TopicRabbitConfig.TOPIC_QUEUE_TWO),
            exchange = @Exchange(value = TopicRabbitConfig.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = TopicRabbitConfig.TOPIC_ROUTING_KEY_ALL
    ))
    public void topicOnMessage2(Map message) {
        logger.info("TopicListener2 received.message:{}", message.toString());
    }

}
