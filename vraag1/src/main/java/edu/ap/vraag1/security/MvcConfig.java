package edu.ap.vraag1.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/details/**").setViewName("weatherStationDetail");
        registry.addViewController("/weather-stations").setViewName("weatherStationsList");
        registry.addViewController("/add-weather-station").setViewName("addWeatherStation");
    }
}
