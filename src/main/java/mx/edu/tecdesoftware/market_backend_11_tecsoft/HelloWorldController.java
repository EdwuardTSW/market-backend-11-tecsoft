package mx.edu.tecdesoftware.market_backend_11_tecsoft;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hola")
    public String hola(){
        return "Hello World";
    }
}
