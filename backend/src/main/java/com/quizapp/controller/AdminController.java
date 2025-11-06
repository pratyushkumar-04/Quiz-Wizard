package com.quizapp.controller;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;



import com.quizapp.dto.LeaderboardEntryDTO;
import com.quizapp.dto.QuestionRequest;
import com.quizapp.model.Category;
import com.quizapp.model.Question;
import com.quizapp.model.User;
import com.quizapp.repository.CategoryRepository;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.ResultRepository;
import com.quizapp.repository.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private  CategoryRepository categoryRepo;

	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/pannel")
	public String index()
	{
		return "Welcome Admin";
	}

	
	@PostMapping("/category/create")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> createCategory(@RequestBody Category category) {
	    categoryRepo.save(category);
	    return ResponseEntity.ok("Category created");
	}

	@GetMapping("/category/all")
	public ResponseEntity<List<Category>> getAllCategories() {
	    return ResponseEntity.ok(categoryRepo.findAll());
	}
	
	 @PostMapping("/question/create")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public ResponseEntity<?> createQuestion(@RequestBody QuestionRequest request) {
	        Optional<Category> categoryOpt = categoryRepo.findById(request.getCategoryId());
	        if (categoryOpt.isEmpty()) {
	            return ResponseEntity.badRequest().body("Invalid category ID");
	        }

	        Question q = new Question();
	        q.setContent(request.getContent());
	        q.setOptionA(request.getOptionA());	
	        q.setOptionB(request.getOptionB());
	        q.setOptionC(request.getOptionC());
	        q.setOptionD(request.getOptionD());
	        q.setCorrectAnswer(request.getCorrectAnswer());
	        q.setCategory(categoryOpt.get());

	        questionRepo.save(q);
	        return ResponseEntity.ok("Question created!");
	    }
	 
	 @GetMapping("/question/by-category/{id}")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 public ResponseEntity<?> getQuestionsByCategory(@PathVariable Long id) {
	     Optional<Category> categoryOpt = categoryRepo.findById(id);
	     if (categoryOpt.isEmpty()) {
	         return ResponseEntity.badRequest().body("Invalid category ID");
	     }

	     List<Question> questions = questionRepo.findByCategory(categoryOpt.get());
	     return ResponseEntity.ok(questions);
	 }
	 
	 // to insert array of questions all at once 
	 @PostMapping("/question/create-bulk")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 public ResponseEntity<?> createQuestionsBulk(@RequestBody List<QuestionRequest> requestList) {
	     List<String> errors = new ArrayList<>();

	     for (int i = 0; i < requestList.size(); i++) {
	         QuestionRequest request = requestList.get(i);
	         Optional<Category> categoryOpt = categoryRepo.findById(request.getCategoryId());

	         if (categoryOpt.isEmpty()) {
	             errors.add("Question " + (i + 1) + ": Invalid category ID " + request.getCategoryId());
	             continue;
	         }

	         Question q = new Question();
	         q.setContent(request.getContent());
	         q.setOptionA(request.getOptionA());
	         q.setOptionB(request.getOptionB());
	         q.setOptionC(request.getOptionC());
	         q.setOptionD(request.getOptionD());
	         q.setCorrectAnswer(request.getCorrectAnswer());
	         q.setCategory(categoryOpt.get());

	         questionRepo.save(q);
	     }

	     if (!errors.isEmpty()) {
	         return ResponseEntity.badRequest().body(errors);
	     }

	     return ResponseEntity.ok("Bulk questions created successfully!");
	 }

	 
	 @GetMapping("/users")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 public ResponseEntity<List<User>> getAllUsers(){
		 return ResponseEntity.ok(userRepo.findAll());
	 }
	 
	 @DeleteMapping("/users/{id}")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 public ResponseEntity<String> deleteUser(@PathVariable Long id) {
	     Optional<User> userOpt = userRepo.findById(id);
	     
	     if (userOpt.isEmpty()) {
	         return ResponseEntity.notFound().build();
	     }

	     userRepo.deleteById(id);
	     return ResponseEntity.ok("User deleted successfully.");
	 }
	 
	 @Autowired
	 private ResultRepository resultRepo;
	 
	 @GetMapping("/leaderboard")
	 public ResponseEntity<List<LeaderboardEntryDTO>> getGlobalLeaderboard() {
	     List<LeaderboardEntryDTO> leaderboard = resultRepo.findLeaderboard();
	     return ResponseEntity.ok(leaderboard.stream().limit(10).toList());
	 }
}

