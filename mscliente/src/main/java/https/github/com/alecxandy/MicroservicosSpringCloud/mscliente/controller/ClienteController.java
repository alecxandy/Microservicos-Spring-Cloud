package https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.controller;

import https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.domain.Cliente;
import https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.domain.dto.ClienteDTO;
import https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.repository.ClienteRepository;
import https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(),clienteDTO.getCpf(),clienteDTO.getIdade());
        clienteService.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping
    public ResponseEntity dadosClientes(@RequestParam String cpf) {
        Optional<Cliente> clienteOptional = clienteService.getByCpf(cpf);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(clienteOptional);
    }

}
