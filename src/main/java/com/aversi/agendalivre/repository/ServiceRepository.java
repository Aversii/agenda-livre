package com.aversi.agendalivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.aversi.agendalivre.domain.entity.ServiceModel;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, String> {

}
