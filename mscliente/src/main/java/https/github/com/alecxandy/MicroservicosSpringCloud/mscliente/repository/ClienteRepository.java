package https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.repository;

import https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
