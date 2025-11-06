package com.quizapp.dto;

public class QuestionAnswerDTO {

	 private Long questionId;
	    private String question; 
	    private String selectedAnswer;
	    private String correctAnswer;
	    private boolean isCorrect;
	    
	    public QuestionAnswerDTO()
	    {}

	    public QuestionAnswerDTO(Long questionId, String selectedAnswer, String correctAnswer, boolean isCorrect) {
			super();
			this.questionId = questionId;
			this.selectedAnswer = selectedAnswer;
			this.correctAnswer = correctAnswer;
			this.isCorrect = isCorrect;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public boolean isCorrect() {
			return isCorrect;
		}

		public void setCorrect(boolean isCorrect) {
			this.isCorrect = isCorrect;
		}

		// Getters and setters
	    public Long getQuestionId() {
	        return questionId;
	    }

	    public void setQuestionId(Long questionId) {
	        this.questionId = questionId;
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

	    public boolean isIsCorrect() {
	        return isCorrect;
	    }

	    public void setIsCorrect(boolean isCorrect) {
	        this.isCorrect = isCorrect;
	    }
}
