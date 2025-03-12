package com.aversi.agendalivre;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class WelcomeController {
    
    @RequestMapping("/")
    public String requestMethodName() {
        return new String("Bem vindo a Agenda Livre!");
    }
    
}
