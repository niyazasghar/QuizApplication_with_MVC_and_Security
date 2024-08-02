package net.javaguides.springboot.service;


import net.javaguides.springboot.Exception.IncorrectPasswordException;
import net.javaguides.springboot.Exception.UserNotFoundException;
import net.javaguides.springboot.dto.PasswordChangeDTO;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordChangeService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    public void savePassword(PasswordChangeDTO passwordChangeDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        if(!passwordEncoder.matches(passwordChangeDTO.getOldPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Old password is incorrect.");
        }
        user.setPassword(passwordEncoder.encode(passwordChangeDTO.getConfirmPassword()));
        userRepository.save(user);
    }
}
