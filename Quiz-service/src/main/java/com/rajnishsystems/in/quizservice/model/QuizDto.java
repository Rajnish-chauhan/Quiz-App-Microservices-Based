package com.rajnishsystems.in.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    String categoryName;
    String title;
    Integer noOfQuestions;
}
