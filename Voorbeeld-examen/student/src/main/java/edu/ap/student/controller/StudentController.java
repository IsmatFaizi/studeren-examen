package edu.ap.student.controller;

import edu.ap.student.jpa.Student;
import edu.ap.student.jpa.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {

        try {
            Iterable<Student> allStudents = studentRepository.findAll();
            List<Student> sortedStudents = StreamSupport.stream(allStudents.spliterator(), false)
                    .sorted(Comparator.comparing(Student::getLastName))
                    .collect(Collectors.toList());
            model.addAttribute("students", sortedStudents);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return "students";
    }

    @GetMapping("/addStudent")
    public String showAddStudent() {
        return "studentForm";
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                             @RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("studyProgram") String studyProgram) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateOfBirth, formatter);
        try {
            Student foundStudent = studentRepository.findByFirstNameAndLastName(firstName, lastName);
            if(foundStudent == null){
                studentRepository.save(new Student(firstName, lastName, date, studyProgram));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return "redirect:/students";
    }


}
