package com.aversi.agendalivre.service;

import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodeService {

    private final PasswordEncoder passwordEncoder;

    public EncodeService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
