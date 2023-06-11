package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.controller;

import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain.Cartao;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.domain.ClienteCartao;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.dto.CartaoSaveRequestDTO;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.dto.CartoesPorClienteResponseDTO;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.service.CartaoService;
import https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.service.ClienteCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private final CartaoService cartaoService;
    @Autowired
    private final ClienteCartaoService clienteCartaoService;

    public CartaoController(CartaoService cartaoService, ClienteCartaoService clienteCartaoService) {
        this.cartaoService = cartaoService;
        this.clienteCartaoService = clienteCartaoService;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CartaoSaveRequestDTO request) {
        Cartao cartao = new Cartao();
        cartao.setBandeiraCartao(request.getBandeira());
        cartao.setNome(request.getNome());
        cartao.setRenda(request.getRenda());
        cartao.setLimiteBasico(request.getLimite());
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartaoRendaAteh(@RequestParam("renda") Long renda) {
        List<Cartao> cartaoList = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.status(HttpStatus.OK).body(cartaoList);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponseDTO>> getCartoesByCLiente(@RequestParam("cpf") String cpf) {
        List<ClienteCartao> clienteCartaoList = clienteCartaoService.findByCpf(cpf);
        List<CartoesPorClienteResponseDTO> cartoesPorClienteResponseDTOList = new ArrayList<>();
        clienteCartaoList.forEach(clienteCartao -> {
            CartoesPorClienteResponseDTO c = new CartoesPorClienteResponseDTO();
            c.setBandeira(clienteCartao.getCartao().getBandeiraCartao().toString());
            c.setNome(clienteCartao.getCartao().getNome());
            c.setLimite(clienteCartao.getLimite());
            cartoesPorClienteResponseDTOList.add(c);
        });
        return ResponseEntity.status(HttpStatus.OK).body(cartoesPorClienteResponseDTOList);
    }


}
