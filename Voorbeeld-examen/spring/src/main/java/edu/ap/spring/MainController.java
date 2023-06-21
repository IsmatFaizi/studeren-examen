package edu.ap.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @GetMapping(value = "/exam/**")
    public String getExamPage(HttpServletRequest request, Model model) {
        String pathVariables = (String) request.getRequestURI();
        pathVariables = pathVariables.replace("/exam/", ""); //remove the /exam/ prefix

        List<String> variables = Arrays.stream(pathVariables.split("/"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        String result = String.join(" -- ", variables);
        model.addAttribute("result", result);
        return "results";
    }

}
