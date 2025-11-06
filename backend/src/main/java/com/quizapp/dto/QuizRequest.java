package com.quizapp.dto;

import java.util.List;

public class QuizRequest {

	 private String title;
	    private String description;
		private Long categoryId;
	    private List<QuestionRequest> questions;
	    public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<QuestionRequest> getQuestions() {
			return questions;
		}
		public void setQuestions(List<QuestionRequest> questions) {
			this.questions = questions;
		}
		public Long getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
}
