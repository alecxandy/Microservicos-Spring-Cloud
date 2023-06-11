package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscrib {

    @RabbitListener(queues = "&{mp.queues.emissao-cartoes}")//qual fila que vai ficar escutando a msg
    public void receberSolicitacaoEmissao(String payload) {
        System.out.println(payload);
    }

}
