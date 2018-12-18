package com.test.Skilters_React.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.Skilters_React.exceptions.EmailAlreadyExistsException;
import com.test.Skilters_React.model.User;
import com.test.Skilters_React.model.UserRepository;

@Service
public class UserMetierImpl implements UserMetier {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User addUser(User user) {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setEmail(user.getEmail());
			return userRepository.save(user);
		} catch (Exception e) {
			throw new EmailAlreadyExistsException("Email '"+user.getEmail()+"' existe deja");
		}
	}

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);;
	}

	@Override
	public List<User> sortByName(Sort sortByIdAsc) {
		return userRepository.findAll(sortByIdAsc);
	}
}
