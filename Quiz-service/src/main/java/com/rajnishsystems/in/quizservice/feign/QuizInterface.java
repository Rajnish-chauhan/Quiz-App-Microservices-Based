package com.rajnishsystems.in.quizservice.feign;

import com.rajnishsystems.in.quizservice.model.QuestionWrapper;
import com.rajnishsystems.in.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuizQus(@RequestParam String categoryName, @RequestParam Integer noOfQus);

    @PostMapping("/question/getQus")
    public ResponseEntity<List<QuestionWrapper>> getQusById(@RequestBody List<Integer> qusIds);

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}