package https.github.com.alecxandy.MicroservicosSpringCloud.git.mscartoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @GetMapping
    public String start() {
        return "OK";
    }


}
