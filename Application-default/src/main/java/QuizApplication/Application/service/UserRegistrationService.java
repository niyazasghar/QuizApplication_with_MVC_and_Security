package QuizApplication.Application.service;

import QuizApplication.Application.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRegistrationService  {
public  void save(User user);
}
