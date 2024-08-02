package QuizApplication.Application.service;

import QuizApplication.Application.entity.Role;
import QuizApplication.Application.entity.User;
import QuizApplication.Application.repo.RoleRepo;
import QuizApplication.Application.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);

        Role role = new Role();
        role.setRole("ROLE_STUDENT");
        role.setUser(user);
        roleRepo.save(role);
    }
}
