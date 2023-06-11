package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.service;

import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain.ClienteCartao;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.repository.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ClienteCartaoService {

    @Autowired
    private ClienteCartaoRepository repository;

    public List<ClienteCartao> findByCpf(String cpf){
        return repository.findByCpf(cpf);
    }




}
