package com.aversi.agendalivre.controller;

import com.aversi.agendalivre.domain.entity.ServiceModel;
import com.aversi.agendalivre.service.ServiceModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceModelController {

    private final ServiceModelService serviceModelService;

    public ServiceModelController(ServiceModelService serviceModelService) {
        this.serviceModelService = serviceModelService;
    }

    // Endpoint para criar um novo serviço
    @PostMapping
    public ResponseEntity<ServiceModel> createService(
            @RequestParam String providerId,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam LocalDateTime serviceDateTime) {

        ServiceModel createdService = serviceModelService.createService(providerId, name, description, price, serviceDateTime);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    // Endpoint para atualizar um serviço existente
    @PatchMapping("/{id}")
    public ResponseEntity<ServiceModel> updateService(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) LocalDateTime serviceDateTime) {

        Optional<ServiceModel> updatedService = serviceModelService.updateService(id, name, description, price, serviceDateTime);

        if (updatedService.isPresent()) {
            return new ResponseEntity<>(updatedService.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para buscar um serviço pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceModel> getServiceById(@PathVariable String id) {
        Optional<ServiceModel> service = serviceModelService.getServiceById(id);

        return service.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para listar todos os serviços
    @GetMapping
    public Iterable<ServiceModel> getAllServices() {
        return serviceModelService.getAllServices();
    }

    // Endpoint para excluir um serviço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable String id) {
        boolean deleted = serviceModelService.deleteService(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
