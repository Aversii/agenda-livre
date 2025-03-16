package com.aversi.agendalivre;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aversi.agendalivre.domain.entity.ServiceModel;
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




          ServiceModel service = ServiceModel.create(
            "provider-123",
            "Corte de Cabelo",
            "Corte de cabelo masculino com tesoura e máquina.",
            new BigDecimal("50.00"),
            LocalDateTime.of(2025, 3, 20, 15, 30) // Data e hora do serviço
        );

        System.out.println("Serviço criado:");
        System.out.println("ID: " + service.getId());
        System.out.println("Prestador: " + service.getProviderId());
        System.out.println("Nome: " + service.getName());
        System.out.println("Descrição: " + service.getDescription());
        System.out.println("Preço: R$ " + service.getPrice());
        System.out.println("Data e Hora: " + service.getServiceDateTime());
        System.out.println("Criado em: " + service.getCreatedAt());
  
    }
}
