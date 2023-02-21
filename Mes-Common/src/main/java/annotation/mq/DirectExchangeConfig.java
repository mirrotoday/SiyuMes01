package annotation.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    public static final String DIRECT_QUEUE = "directQueue";
    public static final String DIRECT_QUEUE2 = "directQueue2";
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String DIRECT_ROUTING_KEY = "direct";

    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE, true);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT_QUEUE2, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingDirectExchange(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with(DIRECT_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDirectExchange2(Queue directQueue2, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with(DIRECT_ROUTING_KEY);
    }

}
