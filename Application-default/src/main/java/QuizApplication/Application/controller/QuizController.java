package QuizApplication.Application.controller;

import QuizApplication.Application.model.Quiz;
import QuizApplication.Application.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/createQuiz")
    public String showCreateQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "/student/quiz/create_quiz";
    }

    @PostMapping("/createQuiz")
    public String createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        quizService.createQuiz(category, numQ, title);
        return "redirect:/student/quizList";
    }

    @GetMapping("/quizList")
    public String getAllQuizzes(Model model) {
        ResponseEntity<List<Quiz>> quizResponse = quizService.showAllQuiz();
        if (quizResponse.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("quizzes", quizResponse.getBody());
        }
        return "/student/quiz/quiz_list";
    }

    @PostMapping("/attemptQuiz")
    public String attemptQuiz(@RequestParam("quizId") Integer quizId, Model model) {
        ResponseEntity<Quiz> quizResponse = quizService.getQuizById(quizId);
        if (quizResponse.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("quiz", quizResponse.getBody());
            return "/student/quiz/view_quiz"; // Thymeleaf template for attempting the quiz
        } else {
            return "redirect:/student/quizList";
        }
    }

    @PostMapping("/submit/{id}")
    public String submitQuiz(@PathVariable Integer id, @RequestParam Map<String, String> responses, Model model) {
        int score = quizService.calculateResult(id, responses);
        model.addAttribute("score", score);
        return "/student/quiz/Quiz_result";
    }
}
