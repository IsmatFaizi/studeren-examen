package edu.ap.vraag1.controller;

import edu.ap.vraag1.jpa.WeatherStation;
import edu.ap.vraag1.jpa.WeatherStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class WeatherStationController {
    @Autowired
    private WeatherStationRepository stationRepository;
    List<String> stationsOption = new ArrayList<String>(
            Arrays.asList("Station one", "Station two", "Station three"));


    @GetMapping("/")
    public String index() {
        return "redirect:/weather-stations";
    }

    @GetMapping("/weather-stations")
    public String weatherStationsList(Model model) {
        model.addAttribute("stations", stationRepository.findAll());
        return "weatherStationsList";
    }

    @GetMapping("/add-weather-station")
    public String showAddForm(Model model) {
        model.addAttribute("stations", stationsOption);
        return "addWeatherStation";
    }

    @PostMapping("/add-weather-station")
    public String addWeatherStation(@RequestParam("name") String name, @RequestParam("date") String date,
                                    @RequestParam("minTemp") double minTemp, @RequestParam("avgTemp") double avgTemp,
                                    @RequestParam("maxTemp") double maxTemp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate convertedDate = LocalDate.parse(date, formatter);
        if (!checkThatStationAllExist(name)) {
            stationRepository.save(new WeatherStation(name, convertedDate, minTemp, avgTemp, minTemp));
        }
        return "redirect:/weather-stations";
    }

    @GetMapping("/detail/{name}")
    public String showDetail(@PathVariable("name") String name, Model model) {
        model.addAttribute("station", stationRepository.findFirstByName(name));
        return "weatherStationDetail";
    }



    public boolean checkThatStationAllExist(String stationName) {
        WeatherStation found = stationRepository.findFirstByName(stationName);
        return found != null;
    }


}
