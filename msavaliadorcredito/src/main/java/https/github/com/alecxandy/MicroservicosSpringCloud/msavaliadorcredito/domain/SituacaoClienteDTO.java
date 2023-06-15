package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SituacaoClienteDTO {
    private DadosClienteDTO cliente;
    private List<CartaoClienteDTO> cartoes;
}

