package com.quizapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quizapp.model.Category;
import com.quizapp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	List<Question> findByCategory(Category category);
	List<Question> findByCategoryId(Long categoryId);
		Optional<Question> findByContent(String content);
		
		@Query(value = "SELECT * FROM question WHERE category_id = :categoryId ORDER BY RAND() LIMIT 10", nativeQuery = true)
		List<Question> findRandom10ByCategoryId(@Param("categoryId") Long categoryId);


}
