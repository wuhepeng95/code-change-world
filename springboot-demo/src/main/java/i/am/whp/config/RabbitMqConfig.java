package i.am.whp.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import i.am.whp.model.MyTable;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuhepeng
 * @date 2019/8/26
 */
@Configuration
public class RabbitMqConfig {

    public static final String WHP_TEST_QUEUE = "whp.test.queue";
    public static final String ROUTING_KEY_NAME = "whp.test.route";

    // 创建Queue
    @Bean
    public Queue whpTestQueue() {
        // 名字 是否持久话（重启不会消失） 是否专有 是否自动删除（长期不用会自动删除）
        return new Queue(WHP_TEST_QUEUE, false, true, true);
    }

    // 定义一个topic交换器
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring.boot.exchange", false, true);
    }

    // 将消息队列queue和exchange绑定
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_NAME);
    }

    // 创建消息监听的容器
    @Bean
    public SimpleRabbitListenerContainerFactory myFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter,
                                                          SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    //消息转换器
    @Bean
    public MessageConverter myMessageConverter() {
        return new MessageConverter() {
            @Override
            public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
                Message message = new Message(JSON.toJSONString(o).getBytes(),messageProperties);
                return message;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return JSONObject.parseObject(message.getBody(),MyTable.class);
            }
        };
    }


}
