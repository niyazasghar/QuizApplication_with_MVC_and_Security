
package QuizApplication.Application.dao;


import QuizApplication.Application.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz,Integer> {


}
