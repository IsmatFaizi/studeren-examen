package edu.ap.customers.controller;

import edu.ap.customers.jpa.Address;
import edu.ap.customers.jpa.AddressRepository;
import edu.ap.customers.jpa.Customer;
import edu.ap.customers.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/customers";
    }

    @GetMapping("/customers")
    public String showCustomers(Model model) {
        try {
            Iterable customers = customerRepository.findAll();
            System.out.println("Customer: " + customers);
            model.addAttribute("customers", customers);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "customers";
    }

    @GetMapping("form")
    public String form() {
        return "form";
    }

    @PostMapping("/form")
    public String saveGrade(@RequestParam("name") String name, @RequestParam("email") String email,
                            @RequestParam("phone") String phone, @RequestParam("age") int age,
                            @RequestParam("projects") String projects, @RequestParam("street") String street,
                            @RequestParam("city") String city, @RequestParam("zipcode") int zipcode,
                            @RequestParam("country") String country, @RequestParam("paymentMethods") String paymentMethods,
                            @RequestParam("gender") String gender, @RequestParam("married") String married,
                            @RequestParam("source") String source) {
        Address addres = new Address(street, city, zipcode, country);
        List<String> projectList = new ArrayList<String>();
        Map<String, String> profileMap = new HashMap<>();
        String[] projectStrings = projects.split(",");
        for (String projectString : projectStrings) {
            projectList.add(projectString);
        }
        List<String> paymentList = new ArrayList<String>();
        String[] paymentStrings = paymentMethods.split(",");
        for (String paymentString : paymentStrings) {
            paymentList.add(paymentString);
        }
        profileMap.put("gender", gender);
        profileMap.put("married", married);
        profileMap.put("source", source);
        Random id = new Random();
        customerRepository.save(new Customer(name, email, phone, age, projectList, addres, paymentList, profileMap));

        return "redirect:/customers";
    }
}
