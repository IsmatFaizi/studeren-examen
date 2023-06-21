package edu.ap.infractions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ap.infractions.Infraction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
public class MainController {
    String path = "src/main/resources/infractions.json";
    List<Infraction> infractionList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/")
    public String index() {
        return "redirect:/infractions";
    }

    @GetMapping("/infractions/{speed}")
    public String showInfractions(@PathVariable("speed") int speed, Model model) {
       if (infractions.size() < 1){
            infractions = Arrays.asList(objectMapper.readValue(data, Infraction[].class));
        }

        String[] intractionsUnderSpeed = infractionList.stream()
                .filter(infraction -> infraction.getInfractions_speed() >= speed)
                .sorted()
                .map(infraction -> "Street: " + infraction.getStreet() + ", Speed limit " + infraction.getSpeed_limit() +
                        ", Infraction speed " + infraction.getInfractions_speed())
                .toArray(String[]::new);

        model.addAttribute("infractions", intractionsUnderSpeed);

        return "infractions";
    }

    @GetMapping("/infractions/form")
    public String showForm() {
        return "infractionsForm";
    }

    @PostMapping("/year")
    public String showByYear(@RequestParam("year") int year, Model model) {
       if (infractions.size() < 1){
            infractions = Arrays.asList(objectMapper.readValue(data, Infraction[].class));
        }

        String[] intractionsUnderSpeed = infractionList.stream()
                .filter(infraction -> infraction.getYear() == year)
                .sorted()
                .map(infraction -> "Street: " + infraction.getStreet() + ", Speed limit " + infraction.getSpeed_limit() +
                        ", Infraction speed " + infraction.getInfractions_speed())
                .toArray(String[]::new);

        model.addAttribute("infractions", intractionsUnderSpeed);

        return "infractions";
    }

}
