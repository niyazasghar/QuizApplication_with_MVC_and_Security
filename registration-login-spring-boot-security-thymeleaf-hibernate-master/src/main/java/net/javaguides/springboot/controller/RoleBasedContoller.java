package net.javaguides.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleBasedContoller {
    @GetMapping("/ADMIN")
    public String mapAdmin(){
        return "admin";
    }
    @GetMapping("/MANAGER")
    public String mapManager(){
        return "manager";
    }
    @GetMapping("/USER")
    public String mapUser(){
        return "employee";
    }

}
