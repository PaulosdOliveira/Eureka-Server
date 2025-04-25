package io.github.paulosdoliveira.msavaliadorcartao.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mqConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String filaEmissao;

    @Bean
    public Queue queueEmissaoCartoes() {
        return new Queue(filaEmissao, true);
    }
}
