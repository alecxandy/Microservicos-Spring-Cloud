package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoClienteDTO {
    private List<CartaoAprovadoDTO> cartoes;
}
