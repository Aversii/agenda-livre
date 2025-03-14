package com.aversi.agendalivre;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aversi.agendalivre.domain.entity.ProductModel;
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



        /* product test */
        ProductModel product = ProductModel.create(
            "123e4567-e89b-12d3-a456-426614174000", // ownerId
            "Eletrônicos",
            "Smartphone XYZ",
            "Um smartphone moderno com câmera de alta resolução.",
            2024,
            "Tech Corp",
            3999.99,
            Arrays.asList("img1.jpg", "img2.jpg")
        );

        // Exibindo os detalhes do produto
        System.out.println("Produto criado:");
        System.out.println("ID: " + product.getId());
        System.out.println("Dono do Produto (Owner ID): " + product.getOwnerId());
        System.out.println("Categoria: " + product.getCategory());
        System.out.println("Nome: " + product.getName());
        System.out.println("Descrição: " + product.getDescription());
        System.out.println("Ano de Lançamento: " + product.getYearOfRelease());
        System.out.println("Autor: " + product.getAuthor());
        System.out.println("Preço: R$ " + product.getPrice());
        System.out.println("Imagens: " + product.getImgs());
        System.out.println("Criado em: " + product.getCreatedAt());

        // Atualizando alguns dados do produto
        product.update(null, "Smartphone XYZ Pro", null, null, "Tech Corp International", 4199.99, null);

        // Exibindo os detalhes atualizados do produto
        System.out.println("\nProduto atualizado:");
        System.out.println("Nome: " + product.getName());
        System.out.println("Autor: " + product.getAuthor());
        System.out.println("Preço: R$ " + product.getPrice());
        System.out.println("Atualizado em: " + product.getUpdatedAt());
    }
}
