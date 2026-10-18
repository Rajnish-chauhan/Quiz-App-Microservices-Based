package com.rajnishsystems.in.questionservice.service;

import com.rajnishsystems.in.questionservice.dao_or_repo.QuestionDao;
import com.rajnishsystems.in.questionservice.model.QuestionWrapper;
import com.rajnishsystems.in.questionservice.model.Questions;
import com.rajnishsystems.in.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Questions>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category){
       try{
           return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
       return  new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestions(Questions questions) {
        try {
            questionDao.save(questions);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("An Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Questions> updateQuestions(Integer id, Questions questions) {
        try {
            questions.setId(id);
            Questions savedQuestion = questionDao.save(questions);
            return new ResponseEntity<>(savedQuestion, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questions, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try{
            questionDao.deleteById(id);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error Occurred",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<Integer>> QuizQus(String categoryName, Integer noOfQus) {
        List<Integer> liquestions=questionDao.getQuestionsByCategory(categoryName,noOfQus);
        return new ResponseEntity<>(liquestions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQusById(List<Integer> qusIds) {
        List<QuestionWrapper> qw=new ArrayList<>();
        List<Questions>qus=new ArrayList<>();
        for (Integer id:qusIds){
            qus.add(questionDao.findById(id).get());
        }

        for (Questions q:qus){
            QuestionWrapper questionWrapper=new QuestionWrapper();
            questionWrapper.setId(q.getId());
            questionWrapper.setQuestionTitle(q.getQuestionTitle());
            questionWrapper.setOption1(q.getOption1());
            questionWrapper.setOption2(q.getOption2());
            questionWrapper.setOption3(q.getOption3());
            questionWrapper.setOption4(q.getOption4());
            qw.add(questionWrapper);
        }
        return new ResponseEntity<>(qw,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        int i=0;
        for (Response response :responses) {
            Questions qus=questionDao.findById(response.getId()).get();
            if (response.getResponses().equals(qus.getRight_answer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
