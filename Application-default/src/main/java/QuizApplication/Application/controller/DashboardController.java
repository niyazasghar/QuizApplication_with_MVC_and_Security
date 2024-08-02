package QuizApplication.Application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String showHome() {

        return "home-page";
    }

    // add a request mapping for /leaders

    @GetMapping("/professor")
    public String showLeaders() {

        return "/dashboard/teacherDashboard";
    }

    @GetMapping("/student")
    public String show() {

        return "/dashboard/studentDashboard";
    }

    // add request mapping for /systems

    @GetMapping("/admin")
    public String showSystems() {

        return "/dashboard/adminDashboard";
    }

}









