package com.rajnishsystems.in.quizservice.service;

import com.rajnishsystems.in.quizservice.dao_or_repo.QuizDao;
import com.rajnishsystems.in.quizservice.feign.QuizInterface;
import com.rajnishsystems.in.quizservice.model.QuestionWrapper;
import com.rajnishsystems.in.quizservice.model.Quiz;
import com.rajnishsystems.in.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer>qus=quizInterface.getQuizQus(category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(qus);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
    Optional<Quiz> quiz=quizDao.findById(id);
    List<Integer> questionIds=quiz.get().getQuestionsIds();
    ResponseEntity<List<QuestionWrapper>>questions=quizInterface.getQusById(questionIds);
    return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer>score=quizInterface.getScore(responses);
        return  score;
    }
}
