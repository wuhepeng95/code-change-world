package i.am.whp.listener.kafka;

import i.am.whp.config.kafka.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author wuhepeng on 2020/2/14
 */
@Component
@Profile("with_kafka")
public class TestListener {

    @KafkaListener(containerFactory = KafkaConfig.KAFKA_LISTENER_CONTAINER_FACTORY,
            topics = "test", groupId = "g1")
    public void test(ConsumerRecord<String, String> record) {
        System.out.println("topic test receive message:" + record.value());
    }

    @KafkaListener(containerFactory = KafkaConfig.KAFKA_LISTENER_CONTAINER_FACTORY,
            topics = "maxwell", groupId = "g1")
    public void maxwell(ConsumerRecord<String, String> record) {
        System.out.println("topic maxwell receive message:" + record.value());
    }

}
