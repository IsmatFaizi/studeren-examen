package edu.ap.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ap.rest.jpa.Infraction;
import edu.ap.rest.jpa.InfractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {
    @Autowired
    private InfractionRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Override
    public void run(String[] args) throws IOException {

        try {
            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            //read JSON file and convert to a customer object
            Infraction[] infraction = objectMapper.readValue(new File("src/main/resources/infractions.json"),
                    Infraction[].class);
            Arrays.stream(infraction).forEach(o -> repo.save(o));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
