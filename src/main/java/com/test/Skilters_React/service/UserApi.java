package com.test.Skilters_React.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.Skilters_React.metier.MapValidationErrorService;
import com.test.Skilters_React.metier.UserMetier;
import com.test.Skilters_React.model.User;
import com.test.Skilters_React.payload.JWTLoginSuccessResponse;
import com.test.Skilters_React.payload.LoginRequest;
import com.test.Skilters_React.security.JwtTokenProvider;

import static com.test.Skilters_React.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/users")
public class UserApi {
	
	@Autowired
	private UserMetier userMetier;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap != null) return errorMap;
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()
				)
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap != null)return errorMap;
		
		User newUser = userMetier.addUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/listusers")
	public List<User> listUsers() {
		return userMetier.listUsers();
	}

	@DeleteMapping(value="/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		userMetier.deleteUser(id);
	}

	@RequestMapping(value="/usersbyname",method=RequestMethod.GET)
	public List<User> sortByName() {
		return userMetier.sortByName(sortByIdAsc());
	}
	
	private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "nom");
    }
}
