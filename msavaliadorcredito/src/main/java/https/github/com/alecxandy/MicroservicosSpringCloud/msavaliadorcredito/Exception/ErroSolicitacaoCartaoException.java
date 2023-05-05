package https.github.com.alecxandy.MicroservicosSpringCloud.msavaliadorcredito.Exception;


public class ErroSolicitacaoCartaoException extends RuntimeException{
    public ErroSolicitacaoCartaoException(String message) {
        super(message);
    }
}