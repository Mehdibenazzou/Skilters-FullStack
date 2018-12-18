package com.test.Skilters_React.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.Skilters_React.model.User;
import com.test.Skilters_React.model.UserRepository;

@Service
public class CustomUserDetailsImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		
		if(user == null) new UsernameNotFoundException("Utilisateur introuvable");
		return user;
	}
	
	@Transactional
	public User loadUserById(Long id) {
		User user = userRepository.getById(id);
		
		if(user == null) new UsernameNotFoundException("Utilisateur introuvable");
		return user;
	}
}
