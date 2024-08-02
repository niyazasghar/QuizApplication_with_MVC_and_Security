package QuizApplication.Application.service;

import QuizApplication.Application.dao.QuestionDao;
import QuizApplication.Application.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        try {
            return questionDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Question> getQuestionsByCategory(String category) {
        try {
            return questionDao.findByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Question getQuestionById(Integer id) {
        return questionDao.findById(id).orElse(null);
    }

    public void addQuestion(Question question) {
        try {
            questionDao.save(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateQuestion(Question question) {
        try {
            questionDao.save(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion(Integer id) {
        try {
            questionDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
