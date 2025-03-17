package com.aversi.agendalivre.domain.entity;

import com.aversi.agendalivre.domain.objectValue.email.Email;
import com.aversi.agendalivre.domain.objectValue.password.Password;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class UserModel {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Email email;
    private Password password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected UserModel(){};
    private UserModel(String id, String firstName, String lastName, Email email, Password password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UserModel create(String firstName, String lastName, String email, String password, PasswordEncoder passwordEncoder) {
        LocalDateTime now = LocalDateTime.now();
        return new UserModel(
            UUID.randomUUID().toString(),
            firstName,
            lastName,
            new Email(email),
            new Password(password, passwordEncoder), // Encoding password here
            now,
            now
        );
    }

    public void update(String firstName, String lastName, String email, String password, PasswordEncoder passwordEncoder) {
        this.firstName = (firstName != null && !firstName.isBlank()) ? firstName : this.firstName;
        this.lastName = (lastName != null && !lastName.isBlank()) ? lastName : this.lastName;
        this.email = (email != null && !email.isBlank()) ? new Email(email) : this.email;
        this.password = (password != null && !password.isBlank()) ? new Password(password, passwordEncoder) : this.password;
        this.updatedAt = LocalDateTime.now();
    }
    

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    
}
