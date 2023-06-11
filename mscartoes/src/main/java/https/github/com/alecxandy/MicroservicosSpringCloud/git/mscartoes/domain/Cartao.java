package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain;

import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.enums.BandeiraCartao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeiraCartao;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao(String nome, BandeiraCartao bandeiraCartao, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeiraCartao = bandeiraCartao;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }
}
