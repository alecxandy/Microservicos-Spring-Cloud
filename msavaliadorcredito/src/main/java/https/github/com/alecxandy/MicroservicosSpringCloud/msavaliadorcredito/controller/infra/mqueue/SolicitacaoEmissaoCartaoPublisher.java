package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.controller.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.domain.DadosSolicitacaoEmissaoCartaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartoes; //fila

    public void solicitarCartao(DadosSolicitacaoEmissaoCartaoDTO dados) throws JsonProcessingException {
        var json = convertIntoJson(dados);
       rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);//a fila que vai mandar e a string
    }

    private String convertIntoJson(DadosSolicitacaoEmissaoCartaoDTO dados) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dados);
        return json;
    }

}