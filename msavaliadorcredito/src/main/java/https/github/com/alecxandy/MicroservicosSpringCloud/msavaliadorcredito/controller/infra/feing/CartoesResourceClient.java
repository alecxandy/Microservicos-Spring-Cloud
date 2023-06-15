package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.controller.infra.feing;


import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.domain.CartaoDTO;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.domain.CartaoClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//via load balance
@FeignClient(value = "mscartoes", path = "/cartoes")//nome da api + caminho onde estamos buscando
public interface CartoesResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoClienteDTO>> getCartoesByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<CartaoDTO>> getCartoesRendaAteh(@RequestParam("renda") Long renda);
}

