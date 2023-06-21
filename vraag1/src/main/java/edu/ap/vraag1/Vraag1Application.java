package edu.ap.vraag1;

import edu.ap.vraag1.jpa.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Vraag1Application {
    @Autowired
    private UsersRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(Vraag1Application.class, args);
    }







}
