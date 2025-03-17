package com.aversi.agendalivre.service;

import com.aversi.agendalivre.domain.entity.UserModel;
import com.aversi.agendalivre.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel createUser(String firstName, String lastName, String email, String password) {
        UserModel user = UserModel.create(firstName, lastName, email, password, passwordEncoder);
        return userRepository.save(user);
    }

    public UserModel updateUser(String userId, String firstName, String lastName, String email, String password) {
        Optional<UserModel> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            UserModel user = userOpt.get();
            user.update(firstName, lastName, email, password, passwordEncoder);
            return userRepository.save(user);
        }
        return null;
    }

    public Optional<UserModel> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public List<UserModel> getAll(){
        return userRepository.findAll();
    }

    public boolean deleteUser(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
