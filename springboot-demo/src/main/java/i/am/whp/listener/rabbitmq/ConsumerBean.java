package i.am.whp.listener.rabbitmq;

import i.am.whp.model.MyTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author wuhepeng
 * @date 2019/8/26
 */
@Component
@Profile("with_mq_redis")
public class ConsumerBean {

    Logger logger = LoggerFactory.getLogger(ConsumerBean.class);

    @RabbitListener(queues = "whp.test.queue", containerFactory = "myFactory")
    public void processMessage(MyTable myTable) {
        logger.info("接受到消息:" + myTable);
    }
}
