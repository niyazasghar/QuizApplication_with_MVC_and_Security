package QuizApplication.Application.repo;

import QuizApplication.Application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role , Long> {

}
