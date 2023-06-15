package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.service;

import feign.FeignException;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.Exception.DadosClienteNotFoundException;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.Exception.ErroComunicacaoMicroservicesException;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.Exception.ErroSolicitacaoCartaoException;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.controller.infra.feing.CartoesResourceClient;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.controller.infra.feing.ClienteResourceClient;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.domain.*;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.controller.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;
    private final CartoesResourceClient cartoesClient;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoClienteDTO obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            //obter dados do cliente
            //obter cartoes do cliente
            ResponseEntity<DadosClienteDTO> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoClienteDTO>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return SituacaoClienteDTO
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoClienteDTO realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {

            ResponseEntity<DadosClienteDTO> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoDTO>> cartoesResponse = cartoesClient.getCartoesRendaAteh(renda);
            List<CartaoDTO> cartoes = cartoesResponse.getBody();

            var listaCartoesAprovados = cartoes.stream().map(cartaoDTO -> {
                DadosClienteDTO dadosClienteDTO = dadosClienteResponse.getBody();
                BigDecimal limiteBasico = cartaoDTO.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosClienteDTO.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);
                CartaoAprovadoDTO aprovado = new CartaoAprovadoDTO();
                aprovado.setCartao(cartaoDTO.getNome());
                aprovado.setBandeira(cartaoDTO.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);
                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoClienteDTO(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartaoDTO solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartaoDTO dados) {
        try {
            emissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartaoDTO(protocolo);
        } catch (Exception e) {
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}
















