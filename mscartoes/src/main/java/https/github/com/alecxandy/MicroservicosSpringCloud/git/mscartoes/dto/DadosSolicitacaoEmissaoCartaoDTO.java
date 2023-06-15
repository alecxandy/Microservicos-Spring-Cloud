package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartaoDTO {
    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
