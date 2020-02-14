package i.am.whp.listener.kafka;

import i.am.whp.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author wuhepeng on 2020/2/14
 */
@Component
public class TestListener {

    @KafkaListener(containerFactory = KafkaConfig.KAFKA_LISTENER_CONTAINER_FACTORY,
            topics = "test", groupId = "g1")
    public void onMessage(ConsumerRecord<String, String> record) {
        System.out.println("receive message:" + record.value());
    }

}
