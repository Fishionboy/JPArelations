package com.example.jpa.relationship.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.relationship.demo.entities.Adress;
import com.example.jpa.relationship.demo.entities.Client;
import com.example.jpa.relationship.demo.entities.Invoice;
import com.example.jpa.relationship.demo.repositories.ClientRepository;
import com.example.jpa.relationship.demo.repositories.InvoiceRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public void run(String... args) throws Exception {
		manyToOneFindByIdClient();
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Fran", "Moras");

		Adress adress1 = new Adress("El verjel", 1234);
		Adress adress2 = new Adress("Vasco de Gama", 986);

		client.getAdresses().add(adress1);
		client.getAdresses().add(adress2);

		clientRepository.save(client);
		System.out.println(client);
	}
	@Transactional
	public void manyToOne() {
		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional	
	public void manyToOneFindByIdClient() {
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}

	}
}
