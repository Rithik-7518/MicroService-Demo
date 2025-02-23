package com.rithik.Question_Service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rithik.Question_Service.dao.QuestionDao;
import com.rithik.Question_Service.model.Question;
import com.rithik.Question_Service.model.QuestionWrapper;
import com.rithik.Question_Service.model.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions) {
		List<Integer> questions=questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
		for(Integer id : questionIds) {
			questions.add(questionDao.findById(id).get());
			
		}
		
		for(Question question : questions) {
			QuestionWrapper wrapper= new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) { 
	    
	    int right = 0;	    
	    for (Response response : responses) {
	    	Question question = questionDao.findById(response.getId()).get();
	    	if (response.getResponse().equalsIgnoreCase(question.getRightAnswer())) {
	    		right++;
	        }
	    }
	    return new ResponseEntity<>(right, HttpStatus.OK);
	}

	

	

}
