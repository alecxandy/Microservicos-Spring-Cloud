package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.controller.infra;

import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.domain.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// O feind faz uma comunicação sicrona(direta) com outro microserviço
@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> dadosCliente(@RequestParam("cpf") String cpf);

}
