package i.am.whp.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
//@RabbitListener(queues = DirectRabbitConfig.QUEUE)
public class DirectReceiver {

    private static Logger logger = LoggerFactory.getLogger(DirectReceiver.class);

//    @RabbitHandler
    public void process(Map message) {
        logger.info("DirectReceiver received message:" + message.toString());
    }

}
