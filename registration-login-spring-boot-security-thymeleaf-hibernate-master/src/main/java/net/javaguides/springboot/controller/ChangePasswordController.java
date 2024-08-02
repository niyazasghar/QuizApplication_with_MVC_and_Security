package net.javaguides.springboot.controller;

import net.javaguides.springboot.Exception.IncorrectPasswordException;
import net.javaguides.springboot.Exception.UserNotFoundException;
import net.javaguides.springboot.dto.PasswordChangeDTO;
import net.javaguides.springboot.service.PasswordChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class ChangePasswordController {
    @Autowired
    private PasswordChangeService passwordChangeService;
    @Autowired
    PasswordChangeDTO passwordDTO;
    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("passwordChangeDTO", new PasswordChangeDTO());
        return "change-Password";
    }
    @PostMapping("/change-password")
    public String chanegePassword(@ModelAttribute PasswordChangeDTO passwordChangeDTO,Model model){
        if(!passwordChangeDTO.getNewPassword().equals((passwordChangeDTO.getConfirmPassword()))) {
             model.addAttribute("errorMessage", "Entered Password dont match");
        }
        else{
           try{
               passwordChangeService.savePassword(passwordChangeDTO);
               model.addAttribute("successMessage", "Password changed successfully.");
           }catch (UserNotFoundException |IncorrectPasswordException e){
               model.addAttribute("errorMessage",e.getMessage());
            }
        }
        return "change-Password";
    }
}
