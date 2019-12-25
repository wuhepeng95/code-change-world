package i.am.whp.config.mq;

public class TopicRabbitConfig {

    public static final String TOPIC_EXCHANGE = "topicExchange";

    public final static String TOPIC_QUEUE_ONE = "topicQueueOne";
    public final static String TOPIC_QUEUE_TWO = "topicQueueTwo";

    public final static String TOPIC_ROUTING_KEY_ONE = "topicRoutingKey.one";
    public final static String TOPIC_ROUTING_KEY_TWO = "topicRoutingKey.two";
    public final static String TOPIC_ROUTING_KEY_ALL = "topicRoutingKey.#";
}
