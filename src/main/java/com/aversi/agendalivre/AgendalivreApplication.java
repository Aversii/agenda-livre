package com.aversi.agendalivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aversi.agendalivre.domain.entity.UserModel;
import com.aversi.agendalivre.service.EncodeService;

@SpringBootApplication
public class AgendalivreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AgendalivreApplication.class, args);

        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        EncodeService encodeService = new EncodeService(passwordEncoder);

        // Teste de criptografia
        String encodedPassword = encodeService.encode("asdasda");
        System.out.println("Senha criptografada: " + encodedPassword);

        // Criando um usuário
        UserModel user = UserModel.create("João", "Silva", "joao@email.com", "senhaForte123.", passwordEncoder);
        System.out.println(user.getFirstName() + " - Criado em: " + user.getCreatedAt());

        // Atualizando o usuário
        user.update("Ze", null, "joao@email.com", "", passwordEncoder);
        System.out.println(user.getFirstName() + " - Atualizado em: " + user.getUpdatedAt());
    }
}
