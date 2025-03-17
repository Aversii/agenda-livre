package com.aversi.agendalivre.service;

import com.aversi.agendalivre.domain.entity.ServiceModel;
import com.aversi.agendalivre.repository.ServiceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ServiceModelService {

    private final ServiceRepository serviceRepository;

    public ServiceModelService(ServiceRepository serviceModelRepository) {
        this.serviceRepository = serviceModelRepository;
    }

    public ServiceModel createService(String providerId, String name, String description, BigDecimal price, LocalDateTime serviceDateTime) {
        ServiceModel service = ServiceModel.create(providerId, name, description, price, serviceDateTime);
        return serviceRepository.save(service);
    }

    public Optional<ServiceModel> updateService(String serviceId, String name, String description, BigDecimal price, LocalDateTime serviceDateTime) {
        Optional<ServiceModel> serviceOptional = serviceRepository.findById(serviceId);
        if (serviceOptional.isPresent()) {
            ServiceModel service = serviceOptional.get();
            service.update(name, description, price, serviceDateTime);
            return Optional.of(serviceRepository.save(service));
        }
        return Optional.empty();
    }

    public Optional<ServiceModel> getServiceById(String serviceId) {
        return serviceRepository.findById(serviceId);
    }

    public Iterable<ServiceModel> getAllServices() {
        return serviceRepository.findAll();
    }

    public boolean deleteService(String serviceId) {
        Optional<ServiceModel> serviceOptional = serviceRepository.findById(serviceId);
        if (serviceOptional.isPresent()) {
            serviceRepository.delete(serviceOptional.get());
            return true;
        }
        return false;
    }
}
