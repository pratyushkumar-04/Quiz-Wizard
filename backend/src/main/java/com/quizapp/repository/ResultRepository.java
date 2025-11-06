package com.quizapp.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quizapp.dto.LeaderboardEntryDTO;
import com.quizapp.model.Result;

import jakarta.transaction.Transactional;

public interface ResultRepository extends JpaRepository<Result, Long>{

	List<Result> findByUserId(Long id);
	List<Result> findTop10ByOrderByScoreDesc();
	List<Result> findByUserUsername(String username);
//	
//	@Query("SELECT new com.quizapp.management.dto.LeaderboardEntryDTO(r.user.username, SUM(r.score)) " +
//		       "FROM Result r GROUP BY r.user.username ORDER BY SUM(r.score) DESC")
//		List<LeaderboardEntryDTO> findTopResults(Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Result r WHERE r.user.id = :userId")
	void deleteByUserId(@Param("userId") Long userId);
	
	  @Query("SELECT new com.quizapp.dto.LeaderboardEntryDTO(r.user.username, SUM(r.score)) " +
	           "FROM Result r GROUP BY r.user.username ORDER BY SUM(r.score) DESC")
	    List<LeaderboardEntryDTO> findLeaderboard();
}

