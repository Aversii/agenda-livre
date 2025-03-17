package com.aversi.agendalivre.domain.objectValue.password;

import com.aversi.agendalivre.error.CustomError;
import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Embeddable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@Embeddable
public class Password {

    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");

    private String passwordData;

    protected Password() { // Empty constructor for Jackson
    }

    @JsonCreator
    public Password(String rawPassword) {
        validatePassword(rawPassword);
        this.passwordData = rawPassword; // Store the raw password for later encoding
    }

    // New constructor that accepts both raw password and PasswordEncoder
    public Password(String rawPassword, PasswordEncoder passwordEncoder) {
        validatePassword(rawPassword);
        this.passwordData = passwordEncoder.encode(rawPassword); // Encode the raw password immediately
    }

    public String getPassword() {
        return passwordData;
    }

    public Password encode(PasswordEncoder passwordEncoder) {
        return new Password(passwordEncoder.encode(this.passwordData));
    }

    public boolean matches(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, this.passwordData);
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new CustomError(400, "A senha não pode ser nula ou vazia");
        }
        if (password.length() < 8) {
            throw new CustomError(400, "A senha deve ter no mínimo 8 caracteres");
        }
        if (!UPPER_CASE.matcher(password).find()) {
            throw new CustomError(400, "A senha deve conter pelo menos uma letra maiúscula");
        }
        if (!LOWER_CASE.matcher(password).find()) {
            throw new CustomError(400, "A senha deve conter pelo menos uma letra minúscula");
        }
        if (!DIGIT.matcher(password).find()) {
            throw new CustomError(400, "A senha deve conter pelo menos um número");
        }
        if (!SPECIAL_CHAR.matcher(password).find()) {
            throw new CustomError(400, "A senha deve conter pelo menos um caractere especial");
        }
    }
}
