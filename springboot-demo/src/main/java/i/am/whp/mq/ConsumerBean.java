package i.am.whp.mq;

import i.am.whp.model.MyTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuhepeng
 * @date 2019/8/26
 */
@Component
public class ConsumerBean {

    Logger logger = LoggerFactory.getLogger(ConsumerBean.class);

    @RabbitListener(queues = "whp.test.queue", containerFactory = "myFactory")
    public void processMessage(MyTable myTable) {
        logger.info("接受到消息:" + myTable);
    }
}
