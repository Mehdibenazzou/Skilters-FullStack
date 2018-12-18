package com.test.Skilters_React.metier;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.test.Skilters_React.model.User;

public interface UserMetier {
	public User addUser(User user);
	public List<User> listUsers();
	public void deleteUser(Long id);
	public List<User> sortByName(Sort sortByIdAsc);
}
