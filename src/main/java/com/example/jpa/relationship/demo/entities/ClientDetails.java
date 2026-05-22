package com.example.jpa.relationship.demo.entities;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jpa.relationship.demo.repositories.ClientDetailsRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="client_details")
public class ClientDetails {

    @Autowired
    ClientDetailsRepository clientDetailsRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean premium;
    private Integer points;
    
    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Client client;

    public ClientDetails() {
    }
    
    public ClientDetails(boolean premium, Integer points) {
        this.premium = premium;
        this.points = points;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isPremium() {
        return premium;
    }
    public void setPremium(boolean premium) {
        this.premium = premium;
    }
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }    

    @Override
    public String toString() {
        return "ClientDetails [id=" + id + ", premium=" + premium + ", points=" + points + "]";
    }

    public ClientDetailsRepository getClientDetailsRepository() {
        return clientDetailsRepository;
    }

    public void setClientDetailsRepository(ClientDetailsRepository clientDetailsRepository) {
        this.clientDetailsRepository = clientDetailsRepository;
    }
    
}
