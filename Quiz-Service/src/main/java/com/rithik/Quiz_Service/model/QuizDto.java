package com.rithik.Quiz_Service.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class QuizDto {

	@JsonProperty("categoryName")
	String categoryName;
	@JsonProperty("numQuestions")
	Integer numQuestions;
	@JsonProperty("title")
	String title;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getNumQuestions() {
		return numQuestions;
	}
	public void setNumQuestions(Integer numQuestions) {
		this.numQuestions = numQuestions;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
