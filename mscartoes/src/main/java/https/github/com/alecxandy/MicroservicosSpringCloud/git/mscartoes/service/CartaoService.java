package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.service;

import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain.Cartao;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao save(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }



}
