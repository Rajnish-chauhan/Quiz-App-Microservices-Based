package com.rajnishsystems.in.quizservice.dao_or_repo;

import com.rajnishsystems.in.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
