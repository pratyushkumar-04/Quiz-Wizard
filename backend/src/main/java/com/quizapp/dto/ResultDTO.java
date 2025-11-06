package com.quizapp.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.quizapp.model.Result;

public class ResultDTO {

	  private Long id;
	    private Long userId;
	    private Long categoryId;
	    private int score;
	    private int questions;
	    private int correctanswers;
	    private LocalDateTime attemptedAt;
	    private List<QuestionAnswerDTO> questionResults;
	    private String question;

	    public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		// Constructor for entity to DTO
	    public ResultDTO(Result result) {
	        this.id = result.getId();
	        this.userId = result.getUser().getId();
	        this.categoryId = result.getCategory().getId();
	        this.score = result.getScore();
	        this.questions = result.getQuestions();
	        this.correctanswers = result.getCorrectanswers();
	        this.attemptedAt = result.getAttemptedAt();
	        this.questionResults = result.getQuestionResults().stream().map(qr -> {
	            QuestionAnswerDTO dto = new QuestionAnswerDTO();
	            dto.setQuestionId(qr.getQuestion().getId());
	            dto.setQuestion(qr.getQuestion().getContent());
	            dto.setSelectedAnswer(qr.getSelectedAnswer());
	            dto.setCorrectAnswer(qr.getCorrectAnswer());
	            dto.setIsCorrect(qr.isCorrect());
	            return dto;
	        }).collect(Collectors.toList());
	    }

	    // Default constructor
	    public ResultDTO() {
	    }

	    // Getters and setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public Long getCategoryId() {
	        return categoryId;
	    }

	    public void setCategoryId(Long categoryId) {
	        this.categoryId = categoryId;
	    }

	    public int getScore() {
	        return score;
	    }

	    public void setScore(int score) {
	        this.score = score;
	    }

	    public int getQuestions() {
	        return questions;
	    }

	    public void setQuestions(int questions) {
	        this.questions = questions;
	    }

	    public int getCorrectanswers() {
	        return correctanswers;
	    }

	    public void setCorrectanswers(int correctanswers) {
	        this.correctanswers = correctanswers;
	    }

	    public LocalDateTime getAttemptedAt() {
	        return attemptedAt;
	    }

	    public void setAttemptedAt(LocalDateTime attemptedAt) {
	        this.attemptedAt = attemptedAt;
	    }

	    public List<QuestionAnswerDTO> getQuestionResults() {
	        return questionResults;
	    }

	    public void setQuestionResults(List<QuestionAnswerDTO> questionResults) {
	        this.questionResults = questionResults;
	    }
}
