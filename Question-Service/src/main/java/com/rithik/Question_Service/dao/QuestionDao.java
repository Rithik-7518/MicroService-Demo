package com.rithik.Question_Service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rithik.Question_Service.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer>{

	List<Question> findByCategory(String category);

	@Query(value="SELECT  id FROM questions WHERE category = ? ORDER BY RAND() LIMIT ?",nativeQuery=true)
	List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
