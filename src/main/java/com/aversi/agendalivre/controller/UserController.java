package com.aversi.agendalivre.controller;

import com.aversi.agendalivre.domain.entity.UserModel;
import com.aversi.agendalivre.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Criar novo usuário
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createUser(@RequestBody UserModel userModel) {
        // O userModel já terá todos os dados para criação
        return userService.createUser(userModel.getFirstName(), userModel.getLastName(),
                userModel.getEmail().getAddress(),
                userModel.getPassword().getPassword());
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAll();
    }

    // Atualizar um usuário existente
    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable String id,
            @RequestBody UserModel userModel) {
        // Passa os dados do userModel para atualizar
        return userService.updateUser(id, userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail().getAddress(),
                userModel.getPassword().getPassword());
    }

    // Buscar um usuário por ID
    @GetMapping("/{id}")
    public Optional<UserModel> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    // Deletar um usuário
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
