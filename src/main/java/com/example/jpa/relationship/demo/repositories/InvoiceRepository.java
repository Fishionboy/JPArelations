package com.example.jpa.relationship.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.relationship.demo.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

    
}