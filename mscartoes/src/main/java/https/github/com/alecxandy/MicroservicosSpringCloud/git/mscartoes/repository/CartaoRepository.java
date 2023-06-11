package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.repository;

import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}
