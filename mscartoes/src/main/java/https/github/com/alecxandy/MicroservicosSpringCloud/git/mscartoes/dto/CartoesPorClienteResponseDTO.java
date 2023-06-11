package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteResponseDTO {

    private String nome;
    private String bandeira;
    private BigDecimal limite;
}
