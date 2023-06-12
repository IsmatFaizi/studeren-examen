package edu.spring.movies.controller;


import edu.spring.movies.redis.RedisService;
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
public class RedisController {

    @Autowired
    private RedisService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/form";
    }

    @GetMapping("/movie")
    public String getForm() {
        return "movieForm";
    }

    @PostMapping("/movie")
    public String postForm(@RequestParam("name") String name, @RequestParam("year") String year,
                           @RequestParam("actors") String actors) {
        String key = "movies:" + name + ":" + year;
        try {
            service.rpush(key, actors);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/movies";
    }

    @GetMapping("/movies")
    public String showMovies(Model model) {
        List<String> movies = new ArrayList<>();

        try {
            Set<String> moviesKeys = service.keys("movies:*");
            for (String movieKey : moviesKeys) {
                String[] movieSplit = movieKey.split(":");
                List<String> actors = service.getList(movieKey);


                String movie = "naam: "+ movieSplit[1] + " jaar: " + movieSplit[2] + " actors: " + actors;
                movies.add(movie);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        model.addAttribute("movies", movies);

        return "movies";
    }


}
