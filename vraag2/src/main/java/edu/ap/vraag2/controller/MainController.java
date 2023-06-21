package edu.ap.vraag2.controller;

import edu.ap.vraag2.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    private RedisService service;


    @GetMapping("/")
    public String index() {

        return "redirect:/show-matches";
    }

    @GetMapping("/show-matches")
    public String showMatches(Model model) {
        List<String> result = new ArrayList<>();
        Set<String> keys = service.keys("match:*");
        keys.forEach(k -> {
            String values = service.getKey(k);
            String teams = k.replace("match:", "").replace(":", " x ");
            result.add("Team: " + teams + " Score: " + values);
        });
        model.addAttribute("matches", result);

        return "showMatches";
    }

    @GetMapping("/arrangement")
    public String showArrangement() {
        return "arrangement";
    }

    @GetMapping("/add-team")
    public String addTeamForm() {
        return "addTeamForm";
    }

    @PostMapping("/add-team")
    public String addTeam(@RequestParam("name") String name, @RequestParam("player1") String player1,
                          @RequestParam("player2") String player2) {
        String key = "team:" + name;

        if (checkIfExist(key)) {
            service.rpush(key, player1);
            service.rpush(key, player2);
        }

        return "redirect:/show-matches";
    }

    @GetMapping("/add-match")
    public String addMatchForm(Model model) {
        List<String> teams = new ArrayList<>();
        Set<String> keys = service.keys("team:*");
        keys.forEach(k -> {
            String teamName = k.replace("team:", "");
            teams.add(teamName);
        });
        model.addAttribute("teams", teams);
        return "addMatchForm";
    }

    @PostMapping("/add-match")
    public String addMatch(@RequestParam("nameTeam1") String nameTeam1, @RequestParam("scoreTeam1") String scoreTeam1,
                           @RequestParam("nameTeam2") String nameTeam2, @RequestParam("scoreTeam2") String scoreTeam2) {
        if(nameTeam2.equals(nameTeam2)){
            return "redirect:/add-match";
        }
        String key = "match:" + nameTeam1 + ":" + nameTeam2;
        String value = scoreTeam1 + "-" + scoreTeam2;
        if (checkIfExist(key)) {
            service.setKey(key, value);
        }
        return "redirect:/show-matches";
    }


    public boolean checkIfExist(String key) {
        Set<String> keys = service.keys(key);
        return keys.isEmpty();
    }
}
