package com.quizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class QuestionResult {

	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String selectedAnswer;
	private String correctAnswer;
	private boolean isCorrect;
	
	@ManyToOne
	@JoinColumn(name="result_id")
	@JsonIgnore
	
	private Result result;
	
	@ManyToOne
    @JoinColumn(name = "question_id")
    private Question question; // Reference instead of String

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	
}
