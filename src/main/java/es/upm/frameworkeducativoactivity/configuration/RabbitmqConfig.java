package es.upm.frameworkeducativoactivity.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Declarables createRabbitmqSchema() {
        return new Declarables(
                new FanoutExchange("user.deleted", true, false, null),
                new Queue("user.deleted.activity"),
                new Binding("user.deleted.activity", Binding.DestinationType.QUEUE, "user.deleted", "", null),

                new FanoutExchange("group.deleted", true, false, null),
                new Queue("group.deleted.activity"),
                new Binding("group.deleted.activity", Binding.DestinationType.QUEUE, "group.deleted", "", null),

                new FanoutExchange("userGroup.deleted", true, false, null),
                new Queue("userGroup.deleted.activity"),
                new Binding("userGroup.deleted.activity", Binding.DestinationType.QUEUE, "userGroup.deleted", "", null));
    }

}
