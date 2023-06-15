package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.infra.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain.Cartao;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain.ClienteCartao;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.dto.DadosSolicitacaoEmissaoCartaoDTO;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.repository.CartaoRepository;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.repository.ClienteCartaoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscrib {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    public EmissaoCartaoSubscrib(CartaoRepository cartaoRepository, ClienteCartaoRepository clienteCartaoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.clienteCartaoRepository = clienteCartaoRepository;
    }

    @RabbitListener(queues = "&{mp.queues.emissao-cartoes}")//qual fila que vai ficar escutando a msg
    public void receberSolicitacaoEmissao(String payload) {
        var mapper = new ObjectMapper();
        try {//se o json não for compativel com dados cai na exception

            //transformando o json em classe
            DadosSolicitacaoEmissaoCartaoDTO dto = mapper.readValue(payload, DadosSolicitacaoEmissaoCartaoDTO.class);
            Cartao cartao = cartaoRepository.findById(dto.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dto.getCpf());
            clienteCartao.setLimite(dto.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
