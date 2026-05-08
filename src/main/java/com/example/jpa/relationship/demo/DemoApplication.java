package com.example.jpa.relationship.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.relationship.demo.entities.Client;
import com.example.jpa.relationship.demo.entities.Invoice;
import com.example.jpa.relationship.demo.repositories.ClientRepository;
import com.example.jpa.relationship.demo.repositories.InvoiceRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public void run(String... args) throws Exception {
		manyToOne();
	}

	public void manyToOne(){
		Client client = new Client("John","Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina",2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}	
}
