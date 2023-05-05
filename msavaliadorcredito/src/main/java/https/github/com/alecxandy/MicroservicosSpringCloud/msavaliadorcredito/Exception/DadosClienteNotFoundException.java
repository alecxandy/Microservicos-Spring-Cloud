package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.Exception;


public class DadosClienteNotFoundException extends Exception{
    public DadosClienteNotFoundException() {
        super("Dados do cliente não encontrados para o CPF informado.");
    }
}
