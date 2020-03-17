package i.am.whp.config.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Profile("with_kafka")
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    public void send(String topic, String taskid, String jsonStr) {
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, taskid, jsonStr);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            //推送成功
            public void onSuccess(SendResult<String, Object> result) {
                logger.info(topic + " 生产者 发送消息成功：" + result.toString());
            }

            @Override
            //推送失败
            public void onFailure(Throwable ex) {
                logger.info(topic + " 生产者 发送消息失败：" + ex.getMessage());
            }
        });
    }
}
