package i.am.whp.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

//@Configuration
@Profile("with_rabbitmq")
public class DirectRabbitConfig {

    public static final String QUEUE = "directQueue";
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String ROUTING_KEY = "routingKey";

    @Bean
    public Queue directQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(ROUTING_KEY);
    }
}
