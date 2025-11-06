package com.quizapp.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.dto.PasswordUpdateDTO;
import com.quizapp.model.Category;
import com.quizapp.model.Question;
import com.quizapp.model.User;
import com.quizapp.repository.CategoryRepository;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }
	
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(Principal principal) {
	    Optional<User> user = userRepo.findByUsername(principal.getName());
	    if (user.isPresent()) {
	        return ResponseEntity.ok(user.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }
	}
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>>getcategories(){
		List<Category> categories =categoryRepo.findAll();
		return ResponseEntity.ok(categories);
	}
	
	@Autowired
	private QuestionRepository Questrepo;
	
	
	
	@GetMapping("/quiz/start/{categoryId}")
	public ResponseEntity<List<Question>> startQuiz(@PathVariable Long categoryId)
	{
		List<Question> questions=Questrepo.findRandom10ByCategoryId(categoryId);
		return ResponseEntity.ok(questions);
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	    return categoryRepo.findById(id)
	        .map(ResponseEntity::ok)
	        .orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/update-profile")
	public ResponseEntity<?> updateProfile(@RequestBody User updatedUser, Principal principal) {
	    Optional<User> optionalUser = userRepo.findByUsername(principal.getName());
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        user.setEmail(updatedUser.getEmail());
	        user.setUsername(updatedUser.getUsername());
	        userRepo.save(user);

	        // ✅ Return JSON so Angular can parse it properly
	        return ResponseEntity.ok(Map.of("message", "User updated successfully"));
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
	    }
	}
	
	@PutMapping("/updatepassword")
	public ResponseEntity<?> updatePwd(@RequestBody PasswordUpdateDTO dto,Principal principal)
	{
		String username=principal.getName();
		Optional<User> optionaluser =userRepo.findByUsername(username);
		
		if(optionaluser.isPresent())
		{
			User user =optionaluser.get();
			if(!passwordEncoder.matches(dto.getOldpwd(), user.getPassword()))
			{
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect");
			}
			user.setPassword(passwordEncoder.encode(dto.getNewpwd()));
			userRepo.save(user);
			
	        return ResponseEntity.ok("Password updated successfully");
		}
		else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}
}

