package com.aversi.agendalivre.domain.objectValue.email;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Embeddable;

import java.util.Objects;
@Embeddable
public class Email {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private String address; // Remover o `final` para permitir a desserialização

    protected Email() { // Construtor padrão para o Jackson
    }

    @JsonCreator
    public Email(String address) {
        if (!isValidAddress(address)) {
            throw new IllegalArgumentException("Endereço de e-mail inválido");
        }
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    private boolean isValidAddress(String address) {
        return address != null && EMAIL_PATTERN.matcher(address).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                '}';
    }
}
