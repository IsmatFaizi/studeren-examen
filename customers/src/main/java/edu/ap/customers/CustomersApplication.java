package edu.ap.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ap.customers.jpa.Address;
import edu.ap.customers.jpa.AddressRepository;
import edu.ap.customers.jpa.Customer;
import edu.ap.customers.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class CustomersApplication implements CommandLineRunner {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomersApplication.class, args);
	}

	@Override
	public void run(String[] args) throws IOException {

		try {
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();

			//read JSON file and convert to a customer object
			Customer[] customersDataFromFile = objectMapper.readValue(new File("src/main/resources/customers.json"),
					Customer[].class);
			for (Customer customerData: customersDataFromFile) {


				customerRepository.save(customerData);

			}
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

}
