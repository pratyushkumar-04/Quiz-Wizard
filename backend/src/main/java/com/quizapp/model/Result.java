package com.quizapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Result {

	@Id @GeneratedValue
	private Long id;
	private int score;
	private int questions;
	private int correctanswers;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "attempted_at")
    private LocalDateTime attemptedAt;
	
	@OneToMany(mappedBy = "result", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<QuestionResult> questionResults = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	
	public List<QuestionResult> getQuestionResults() {
		return questionResults;
	}

	public void setQuestionResults(List<QuestionResult> questionResults) {
		this.questionResults = questionResults;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public LocalDateTime getAttemptedAt() {
		return attemptedAt;
	}

	public void setAttemptedAt(LocalDateTime attemptedAt) {
		this.attemptedAt = attemptedAt;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
