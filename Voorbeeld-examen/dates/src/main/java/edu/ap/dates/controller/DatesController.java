package edu.ap.dates.controller;

import edu.ap.dates.jpa.Dates;
import edu.ap.dates.jpa.DatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DatesController {
    @Autowired
    private DatesRepository repository;

    @GetMapping("/")
    public String index() {
        return "redirect:/addDate";
    }

    @GetMapping("/addDate")
    public String addDate() {
        return "dateForm";
    }

    @GetMapping("/{startDate}/{endDate}/{checkDate}")
    public String showCheck(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate,
                            @PathVariable("checkDate") String checkDate, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDateConverted = LocalDate.parse(startDate, formatter);
        LocalDate endDateConverted = LocalDate.parse(endDate, formatter);
        LocalDate checkDateConverted = LocalDate.parse(checkDate, formatter);
        LocalDate now = LocalDate.now();

        List<String> data = new ArrayList<>();
        String yesOrNo;

        long days = ChronoUnit.DAYS.between(checkDateConverted, now);

        if (checkDateConverted.isAfter(startDateConverted) && checkDateConverted.isBefore(endDateConverted)) {
            yesOrNo = "Yes";
        } else {
            yesOrNo = "No";
        }
        data.add(yesOrNo);
        data.add(String.valueOf(days));
        model.addAttribute("data", data);
        try {
            repository.save(new Dates(checkDateConverted, yesOrNo, days));
        } catch (Exception e) {
            System.out.println(e);
        }
        return "checkDate";
    }

    @PostMapping("/addDate")
    public String postDate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                           @RequestParam("checkDate") String checkDate) {
        return "redirect:/" + startDate + "/" + endDate + "/" + checkDate;

    }
}
