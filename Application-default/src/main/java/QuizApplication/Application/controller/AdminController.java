package QuizApplication.Application.controller;

import QuizApplication.Application.model.Question;
import QuizApplication.Application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/allQuestions")
    public String viewAllQuestions(Model model) {
        List<Question> questions = questionService.getAllQuestions(); // or however you fetch the questions
        model.addAttribute("questions", questions);
        return "admin/view_all_questions";
    }


    // Show Form to Add a New Question
    @GetMapping("/addQuestion")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "admin/add_question"; // Ensure this matches the Thymeleaf template name
    }

    // Add a New Question
    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute Question question) {
        questionService.addQuestion(question);
        return "redirect:/admin/allQuestions"; // Redirect to the admin view all questions page
    }

    // Show Form to Edit a Question
    @GetMapping("/editQuestion/{id}")
    public String showEditQuestionForm(@PathVariable("id") Integer id, Model model) {
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        return "admin/edit_question"; // Ensure this matches the Thymeleaf template name
    }

    // Update a Question
    @PostMapping("/editQuestion")
    public String editQuestion(@ModelAttribute Question question) {
        questionService.updateQuestion(question);
        return "redirect:/admin/allQuestions"; // Redirect to the admin view all questions page
    }

    // Delete a Question
    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable("id") Integer id) {
        questionService.deleteQuestion(id);
        return "redirect:/admin/allQuestions"; // Redirect to the admin view all questions page
    }


}
