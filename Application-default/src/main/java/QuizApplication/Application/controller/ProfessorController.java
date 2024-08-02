package QuizApplication.Application.controller;

import QuizApplication.Application.model.Question;
import QuizApplication.Application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private QuestionService questionService;

    // View All Questions
    @GetMapping("/allQuestions")
    public String viewQuestions(Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "questions"; // Thymeleaf template to view questions
    }


    // View Questions by Category
    @GetMapping("/category")
    public String getQuestionsByCategory(@RequestParam String category, Model model) {
        List<Question> questions = questionService.getQuestionsByCategory(category);
        model.addAttribute("questions", questions);
        return "questions"; // Thymeleaf template to view questions by category
    }
}
