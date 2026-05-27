package com.example.jpa.relationship.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.relationship.demo.entities.Adress;
import com.example.jpa.relationship.demo.entities.Client;
import com.example.jpa.relationship.demo.entities.ClientDetails;
import com.example.jpa.relationship.demo.entities.Course;
import com.example.jpa.relationship.demo.entities.Invoice;
import com.example.jpa.relationship.demo.entities.Student;
import com.example.jpa.relationship.demo.repositories.ClientDetailsRepository;
import com.example.jpa.relationship.demo.repositories.ClientRepository;
import com.example.jpa.relationship.demo.repositories.CourseRepository;
import com.example.jpa.relationship.demo.repositories.InvoiceRepository;
import com.example.jpa.relationship.demo.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final ClientDetailsRepository clientDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	DemoApplication(ClientDetailsRepository clientDetailsRepository) {
		this.clientDetailsRepository = clientDetailsRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		oneToMany();
	}

	@Transactional
	public void manyToManyRemoveBidireccionalFind() {		
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentRepository.findOneWithCourses(1L).get();
		Student student2 = studentOptional2.get();		

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		// System.out.println(student1);
		// System.out.println(student2);

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1,student2));

		Optional <Student> studenOptionalDb = studentRepository.findOneWithCourses(1L);
		if(studenOptionalDb.isPresent()){
			Student studenDb = studenOptionalDb.get();
			Optional<Course> courseOptionalDB = courseRepository.findOneWithStudents(1L);

			if(courseOptionalDB.isPresent()){
				Course courseDb = courseOptionalDB.get();
				studenDb.removeCourse(courseDb);

				studentRepository.save(studenDb);
				System.out.println(studenDb);
			}
		}
	}
	@Transactional
	public void manyToManyBidireccionalFind() {		
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentRepository.findOneWithCourses(1L).get();
		Student student2 = studentOptional2.get();		

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		// System.out.println(student1);
		// System.out.println(student2);

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1,student2));
	}

		@Transactional
	public void manyToManyBidireccional() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curse de java master", "Andres");
		Course course2 = new Course("Curse de Spring Boot", "Andres");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}
		@Transactional
	public void manyToManyBidireccionalRemove() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curse de java master", "Andres");
		Course course2 = new Course("Curse de Spring Boot", "Andres");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional <Student> studenOptionalDb = studentRepository.findOneWithCourses(3L);
		if(studenOptionalDb.isPresent()){
			Student studenDb = studenOptionalDb.get();
			Optional<Course> courseOptionalDB = courseRepository.findOneWithStudents(3L);

			if(courseOptionalDB.isPresent()){
				Course courseDb = courseOptionalDB.get();
				studenDb.removeCourse(courseDb);

				studentRepository.save(studenDb);
				System.out.println(studenDb);
			}
		}
	}

		@Transactional
	public void manyToManyRemove() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curse de java master", "Andres");
		Course course2 = new Course("Curse de Spring Boot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional <Student> studenOptionalDb = studentRepository.findOneWithCourses(3L);
		if(studenOptionalDb.isPresent()){
			Student studenDb = studenOptionalDb.get();
			Optional<Course> courseOptionalDB = courseRepository.findById(3L);

			if(courseOptionalDB.isPresent()){
				Course courseDb = courseOptionalDB.get();
				studenDb.getCourses().remove(courseDb);

				studentRepository.save(studenDb);
				System.out.println(studenDb);
			}
		}
	}

	@Transactional
	public void manyToManyRemoveFind() {
		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Optional<Course> courseOptional1 = courseRepository.findById(1L);
		Optional<Course> courseOptional2 = courseRepository.findById(2L);

		Course course1 = courseOptional1.get();
		Course course2 = courseOptional2.get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional <Student> studenOptionalDb = studentRepository.findOneWithCourses(1L);
		if(studenOptionalDb.isPresent()){
			Student studenDb = studenOptionalDb.get();
			Optional<Course> courseOptionalDB = courseRepository.findById(2L);

			if(courseOptionalDB.isPresent()){
				Course courseDb = courseOptionalDB.get();
				studenDb.getCourses().remove(courseDb);

				studentRepository.save(studenDb);
				System.out.println(studenDb);
			}
		}
	}

	@Transactional
	public void manyToManyFind() {
		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Optional<Course> courseOptional1 = courseRepository.findById(1L);
		Optional<Course> courseOptional2 = courseRepository.findById(2L);

		Course course1 = courseOptional1.get();
		Course course2 = courseOptional2.get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToMany() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curse de java master", "Andres");
		Course course2 = new Course("Curse de Spring Boot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void oneToOneBidireccional() {

		Client client = new Client("Erba", "Pura");
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		client.setClientdDetails(clientDetails);

		clientDetailsRepository.save(clientDetails);
		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindById() {
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOne(2L);
		clientOptional.ifPresent(client -> {
			client.setClientdDetails(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOne() {
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientdDetails(clientDetails);
		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeInvoiceBidireccional() {
		Optional<Client> optionalClient = Optional.of(new Client("Michael", "Jackson"));

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de las casa", 5000L, client);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L, client);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});

		Optional<Client> optionalClientBd = clientRepository.findOne(1L);

		optionalClientBd.ifPresent(client -> {
			Invoice invoice3 = new Invoice("Compras de las casa", 5000L, client);
			invoice3.setId(1L);
			Optional<Invoice> invoiceOptional = Optional.of(invoice3);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});
		});
	}

	@Transactional
	public void removeInvoiceBidireccionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de las casa", 5000L, client);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L, client);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});

		Optional<Client> optionalClientBd = clientRepository.findOne(1L);

		optionalClientBd.ifPresent(client -> {
			Invoice invoice3 = new Invoice("Compras de las casa", 5000L, client);
			invoice3.setId(1L);
			Optional<Invoice> invoiceOptional = Optional.of(invoice3);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {
		Optional<Client> optionalClient = clientRepository.findById(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de las casa", 5000L, client);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L, client);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de las casa", 5000L, client);
		Invoice invoice2 = new Invoice("Compras de oficina", 8000L, client);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Fran", "Moras");

		Adress adress1 = new Adress("El verjel", 1234);
		Adress adress2 = new Adress("Vasco de Gama", 986);

		client.getAdresses().add(adress1);
		client.getAdresses().add(adress2);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Adress adress1 = new Adress("El verjel", 1234);
		Adress adress2 = new Adress("Vasco de Gama", 986);

		client.getAdresses().add(adress1);
		client.getAdresses().add(adress2);

		clientRepository.save(client);
		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAdresses().remove(adress1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findOneWithInvoices(1L);
		optionalClient.ifPresent(client -> {
			Adress adress1 = new Adress("El verjel", 1234);
			Adress adress2 = new Adress("Vasco de Gama", 986);

			Set<Adress> addresses = new HashSet<>();
			addresses.add(adress1);
			addresses.add(adress2);
			client.setAdresses(addresses);

			clientRepository.save(client);
			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				c.getAdresses().remove(adress2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}

	@Transactional
	public void oneToManyInvoiceFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Adress adress1 = new Adress("El verjel", 1234);
			Adress adress2 = new Adress("Vasco de Gama", 986);

			Set<Adress> addresses = new HashSet<>();
			addresses.add(adress1);
			addresses.add(adress2);
			client.setAdresses(addresses);

			clientRepository.save(client);
			System.out.println(client);
		});

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
