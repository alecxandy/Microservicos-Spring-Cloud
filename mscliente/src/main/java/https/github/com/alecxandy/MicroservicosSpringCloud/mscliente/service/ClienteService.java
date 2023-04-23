package https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.service;

import https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.domain.Cliente;
import https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getByCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }
}
