package edu.ap.springgrade.controller;

import edu.ap.springgrade.jpa.Grade;
import edu.ap.springgrade.jpa.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    @Autowired
    private GradeRepository gradeRepo;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String showListOfGrades(Model model) {
        model.addAttribute("grades", gradeRepo.findAll());

        return "list";
    }

    @RequestMapping("/{firstName}/{lastName}")
    public String showDetails(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
                             Model model){
        Grade gradeFromDB = gradeRepo.findByFirstNameAndLastName(firstName, lastName);
        model.addAttribute("grade", gradeFromDB);

        return "detail";
    }

    @RequestMapping("grade")
    public String addGrade() {
        return "gradeForm";
    }

    @PostMapping("grade")
    public String saveGrade(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                            @RequestParam("grade") int grade) {

        try {
            Grade newGrade = new Grade(firstName, lastName, grade);
            gradeRepo.save(newGrade);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "redirect:/list";

    }
}
