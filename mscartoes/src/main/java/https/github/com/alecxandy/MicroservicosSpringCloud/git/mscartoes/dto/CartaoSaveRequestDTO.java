package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.dto;

import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.enums.BandeiraCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoSaveRequestDTO {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;


}
