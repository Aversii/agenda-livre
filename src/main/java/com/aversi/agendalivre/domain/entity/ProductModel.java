package com.aversi.agendalivre.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProductModel {

    private String id;
    private String ownerId;
    private String category;
    private String name;
    private String description;
    private int yearOfRelease;
    private String author;
    private double price;
    private List<String> imgs;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProductModel(String id, String ownerId, String category, String name, String description, int yearOfRelease, String author, double price, List<String> imgs, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.category = category;
        this.name = name;
        this.description = description;
        this.yearOfRelease = yearOfRelease;
        this.author = author;
        this.price = price;
        this.imgs = imgs;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ProductModel create(String ownerId, String category, String name, String description, int yearOfRelease, String author, double price, List<String> imgs) {
        LocalDateTime now = LocalDateTime.now();
        return new ProductModel(
            UUID.randomUUID().toString(),
            ownerId,
            category,
            name,
            description,
            yearOfRelease,
            author,
            price,
            imgs,
            now,
            now
        );
    }

    public void update(String category, String name, String description, Integer yearOfRelease, String author, Double price, List<String> imgs) {
        this.category = (category != null && !category.isBlank()) ? category : this.category;
        this.name = (name != null && !name.isBlank()) ? name : this.name;
        this.description = (description != null && !description.isBlank()) ? description : this.description;
        this.yearOfRelease = (yearOfRelease != null) ? yearOfRelease : this.yearOfRelease;
        this.author = (author != null && !author.isBlank()) ? author : this.author;
        this.price = (price != null) ? price : this.price;
        this.imgs = (imgs != null && !imgs.isEmpty()) ? imgs : this.imgs;
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }
    
    public String getOwnerId() {
        return ownerId;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}