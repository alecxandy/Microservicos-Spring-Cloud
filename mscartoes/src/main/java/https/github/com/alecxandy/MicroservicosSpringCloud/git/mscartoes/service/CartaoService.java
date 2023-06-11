package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.service;

import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain.Cartao;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class CartaoService {

    @Autowired
    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public Cartao save(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda){
        var rendaBigDecimal = BigDecimal.valueOf(renda);
         return cartaoRepository.findByRendaLessThanEqual(rendaBigDecimal);
    }



}
