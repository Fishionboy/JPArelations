package com.example.jpa.relationship.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jpa.relationship.demo.entities.ClientDetails;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails,Long>{

}
