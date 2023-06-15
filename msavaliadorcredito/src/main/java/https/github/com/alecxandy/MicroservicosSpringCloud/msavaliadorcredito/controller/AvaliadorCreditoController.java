package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.controller;


import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.Exception.DadosClienteNotFoundException;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.Exception.ErroComunicacaoMicroservicesException;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.Exception.ErroSolicitacaoCartaoException;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.domain.*;
import https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class  AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultarSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
            SituacaoClienteDTO situacaoClienteDTO = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoClienteDTO);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao( @RequestBody DadosAvaliacaoDTO dados ){
        try {
            RetornoAvaliacaoClienteDTO retornoAvaliacaoClienteDTO = avaliadorCreditoService
                    .realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoClienteDTO);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("solicitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartaoDTO dados){
        try{
            ProtocoloSolicitacaoCartaoDTO protocoloSolicitacaoCartaoDTO = avaliadorCreditoService
                    .solicitarEmissaoCartao(dados);
            return ResponseEntity.ok(protocoloSolicitacaoCartaoDTO);
        }catch (ErroSolicitacaoCartaoException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
