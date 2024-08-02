package QuizApplication.Application.controller;

import QuizApplication.Application.entity.User;
import QuizApplication.Application.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register-form"; // Ensure this matches the actual template name
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userRegistrationService.save(user);
        return "redirect:/showMyLoginPage";
    }
}
