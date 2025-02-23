package com.rithik.Quiz_Service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rithik.Quiz_Service.model.Quiz;


public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
