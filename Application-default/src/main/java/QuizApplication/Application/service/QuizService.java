package QuizApplication.Application.service;

import QuizApplication.Application.dao.QuestionDao;
import QuizApplication.Application.dao.QuizDao;
import QuizApplication.Application.model.Question;
import QuizApplication.Application.model.QuestionWrapper;
import QuizApplication.Application.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Quiz>> showAllQuiz() {
        List<Quiz> quizzes = quizDao.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    public String createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return "Quiz created successfully!";
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isPresent()) {
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsForUser.add(qw);
            }
            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Quiz> getQuizById(Integer quizId) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        if (quiz.isPresent()) {
            return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public int calculateResult(Integer id, Map<String, String> responses) {
        Optional<Quiz> quizOptional = quizDao.findById(id);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            List<Question> questions = quiz.getQuestions();
            int right = 0;

            for (Question question : questions) {
                // Build the expected key for this question's response
                String key = "answers[" + question.getId() + "]";

                // Get the selected answer for this question
                String selectedAnswer = responses.get(key);

                // Check if the selected answer matches the correct answer
                if (selectedAnswer != null && selectedAnswer.equals(question.getRightAnswer())) {
                    right++;
                }
            }

            return right;
        } else {
            // Quiz not found
            return 0;
        }
    }
}
