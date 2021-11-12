package com.example.billlingservice.dao;

import com.example.billlingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Billrepository extends JpaRepository<Bill,Long> {



}
