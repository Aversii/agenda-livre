package com.aversi.agendalivre.domain.objectValue.password;

import com.aversi.agendalivre.error.CustomError;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class Password {

    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");

    private final String passwordData;

    public Password(String rawPassword, PasswordEncoder passwordEncoder) {
        validatePassword(rawPassword);
        this.passwordData = passwordEncoder.encode(rawPassword);
    }

    public String getPassword() {
        return passwordData;
    }

    public Password changePassword(String newPassword, PasswordEncoder passwordEncoder) {
        return new Password(newPassword, passwordEncoder);
    }

    public boolean matches(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, this.passwordData);
    }

    private void validatePassword(String password) {
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
