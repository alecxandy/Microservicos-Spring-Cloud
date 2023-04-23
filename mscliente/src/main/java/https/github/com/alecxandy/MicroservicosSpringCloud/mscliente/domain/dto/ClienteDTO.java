package https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.domain.dto;

import https.github.com.alecxandy.MicroservicosSpringCloud.mscliente.domain.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private Integer idade;

    public Cliente toModel() {
        return new Cliente(nome, cpf, idade);
    }
}
