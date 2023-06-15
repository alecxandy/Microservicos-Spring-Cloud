package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartoes}")//injetando valor do aplication.yml
    private String emissaoCartoesFila;

/*  O @Bean serve para exportar uma classe para o Spring,
    para que ele consiga carregar essa classe e fazer injeção
    de dependência dela em outra classes.*/
    @Bean
    public Queue queueEmissaoCartoes(){
        return new Queue(emissaoCartoesFila,true);//nome da fila e durabilidade
    }
}
