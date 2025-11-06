package com.quizapp.controller;

import org.springframework.data.domain.Pageable;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.dto.LeaderboardEntryDTO;
import com.quizapp.dto.ResultDTO;
import com.quizapp.model.Category;
import com.quizapp.model.Question;
import com.quizapp.model.QuestionResult;
import com.quizapp.model.Result;
import com.quizapp.model.User;
import com.quizapp.repository.CategoryRepository;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.ResultRepository;
import com.quizapp.repository.UserRepository;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/user/result")
@CrossOrigin("*")
public class ResultController {

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResultRepository resultRepo;

 // Handles POST requests to save a quiz attempt with per-question details
    @PostMapping("/save")
    public ResponseEntity<?> saveResult(@RequestBody ResultDTO resultDTO, Principal principal) {
        try {
            // Validate the authenticated user using the JWT token's username
            Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
            if (optionalUser.isEmpty()) {
                // Return 404 if user is not found in the database
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Validate the category using the categoryId from the incoming DTO
            Optional<Category> optionalCategory = categoryRepo.findById(resultDTO.getCategoryId());
            if (optionalCategory.isEmpty()) {
                // Return 400 if category is not found
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
            }

            // Extract user and category entities for the Result
            User user = optionalUser.get();
            Category category = optionalCategory.get();

            // Create a new Result entity to store quiz attempt data
            Result result = new Result();
            result.setUser(user); // Link to authenticated user
            result.setCategory(category); // Link to quiz category
            result.setScore(resultDTO.getScore()); // Set score from frontend DTO
            result.setQuestions(resultDTO.getQuestions()); // Set total questions
            result.setAttemptedAt(LocalDateTime.now()); // Record attempt timestamp

            // Track correct answers for backend validation
            AtomicInteger correctCount = new AtomicInteger();

            // Convert QuestionAnswerDTOs to QuestionResult entities for per-question details
            List<QuestionResult> questionResults = resultDTO.getQuestionResults().stream().map(dto -> {
                // Ensure questionId is not null
                if (dto.getQuestionId() == null) {
                    throw new IllegalArgumentException("Question ID cannot be null");
                }

                // Fetch the Question entity to validate and get correct answer
                Optional<Question> qOpt = questionRepo.findById(dto.getQuestionId());
                if (qOpt.isEmpty()) {
                    throw new IllegalArgumentException("Question not found for ID: " + dto.getQuestionId());
                }

                // Create a QuestionResult entity for this question
                Question question = qOpt.get();
                QuestionResult qr = new QuestionResult();
                qr.setResult(result); // Link to parent Result entity
                qr.setQuestion(question); // Reference the Question entity (for question_id foreign key)
                qr.setSelectedAnswer(dto.getSelectedAnswer()); // User's selected answer
                qr.setCorrectAnswer(question.getCorrectAnswer()); // Correct answer from Question entity

                // Verify correctness on backend to ensure accuracy
                boolean isCorrect = dto.getSelectedAnswer() != null &&
                                    dto.getSelectedAnswer().equals(question.getCorrectAnswer());
                qr.setCorrect(isCorrect); // Set correctness flag
                if (isCorrect) {
                    correctCount.incrementAndGet(); // Increment correct answer count
                }

                return qr;
            }).collect(Collectors.toList());

            // Assign question results and validated correct answer count to Result
            result.setQuestionResults(questionResults);
            result.setCorrectanswers(correctCount.get()); // Override frontend's correctAnswers with backend count

            // Save the Result entity (cascades to save QuestionResult entities)
            Result savedResult = resultRepo.save(result);

            // Convert saved Result to DTO for response
            ResultDTO savedResultDTO = new ResultDTO(savedResult);
            return ResponseEntity.ok(savedResultDTO); // Return 200 OK with saved result

        } catch (IllegalArgumentException e) {
            // Handle validation errors (e.g., null questionId, invalid question)
            System.err.println("Error processing result: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            // Handle unexpected errors (e.g., database issues)
            System.err.println("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving result: " + e.getMessage());
        }
    }


    @GetMapping("/previousattempts")
    public ResponseEntity<List<ResultDTO>> pastAttempts(Principal principal) {
        try {
            List<Result> results = resultRepo.findByUserUsername(principal.getName());
            List<ResultDTO> dtoList = results.stream().map(ResultDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            System.err.println("Error fetching past attempts: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
        
    }
    @GetMapping("/{resultId}")
    public ResponseEntity<?> getResultById(@PathVariable Long resultId, Principal principal) {
        Optional<Result> optionalResult = resultRepo.findById(resultId);

        if (optionalResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Result not found");
        }

        Result result = optionalResult.get();

        // Optional: verify user matches
        if (!result.getUser().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not allowed");
        }

        return ResponseEntity.ok(result);
    }
    @GetMapping("/leaderboard")
	 public ResponseEntity<List<LeaderboardEntryDTO>> getGlobalLeaderboard() {
	     List<LeaderboardEntryDTO> leaderboard = resultRepo.findLeaderboard();
	     return ResponseEntity.ok(leaderboard.stream().limit(10).toList());
	 }
}
