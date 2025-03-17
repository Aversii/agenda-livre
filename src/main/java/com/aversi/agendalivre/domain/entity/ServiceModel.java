package com.aversi.agendalivre.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ServiceModel {

    @Id
    private String id;
    private String providerId;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime serviceDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ServiceModel(String id, String providerId, String name, String description, BigDecimal price,
            LocalDateTime serviceDateTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.providerId = providerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.serviceDateTime = serviceDateTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ServiceModel create(String providerId, String name, String description, BigDecimal price,
            LocalDateTime serviceDateTime) {
        LocalDateTime now = LocalDateTime.now();
        return new ServiceModel(
                UUID.randomUUID().toString(),
                providerId,
                name,
                description,
                price,
                serviceDateTime,
                now,
                now);
    }

    public void update(String name, String description, BigDecimal price, LocalDateTime serviceDateTime) {
        this.name = (name != null && !name.isBlank()) ? name : this.name;
        this.description = (description != null && !description.isBlank()) ? description : this.description;
        this.price = (price != null) ? price : this.price;
        this.serviceDateTime = (serviceDateTime != null) ? serviceDateTime : this.serviceDateTime;
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getServiceDateTime() {
        return serviceDateTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
