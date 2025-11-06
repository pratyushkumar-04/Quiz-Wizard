package com.quizapp.dto;

public class LeaderboardEntryDTO {

    private String username;
    private Long totalScore;

    public LeaderboardEntryDTO(String username, Long totalScore) {
        this.username = username;
        this.totalScore = totalScore;
    }

    public String getUsername() {
        return username;
    }

    public Long getTotalScore() {
        return totalScore;
    }
    
}