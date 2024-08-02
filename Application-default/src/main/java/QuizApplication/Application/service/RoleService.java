package QuizApplication.Application.service;

import QuizApplication.Application.entity.Role;
import QuizApplication.Application.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;
    public void save(Role role){
        roleRepo.save((role));
    }
}
