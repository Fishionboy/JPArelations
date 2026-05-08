package com.example.jpa.relationship.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.relationship.demo.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

}
