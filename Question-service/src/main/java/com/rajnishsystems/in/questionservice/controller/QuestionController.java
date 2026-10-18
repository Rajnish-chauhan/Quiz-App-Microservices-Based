package com.rajnishsystems.in.questionservice.controller;

import com.rajnishsystems.in.questionservice.model.QuestionWrapper;
import com.rajnishsystems.in.questionservice.model.Questions;
import com.rajnishsystems.in.questionservice.model.Response;
import com.rajnishsystems.in.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("qus")
    public ResponseEntity<List<Questions>> getAllQuestions(){
       return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestions(@RequestBody Questions questions){
        return questionService.addQuestions(questions);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Questions> updateQuestions(@PathVariable Integer id,@RequestBody Questions questions){
        return questionService.updateQuestions(id,questions);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestions(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuizQus(@RequestParam String categoryName,@RequestParam Integer noOfQus){
        return questionService.QuizQus(categoryName,noOfQus);
    }

    @PostMapping("/getQus")
    public ResponseEntity<List<QuestionWrapper>> getQusById(@RequestBody List<Integer> qusIds){
        return questionService.getQusById(qusIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
