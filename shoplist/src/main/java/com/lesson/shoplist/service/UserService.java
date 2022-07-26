package com.lesson.shoplist.service;

import com.lesson.shoplist.persist.User;
import com.lesson.shoplist.persist.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepo userRepo;

	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public void create(UserRepr userRepr) {
		User user = new User();
		user.setUsername(userRepr.getUsername());
		user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
		userRepo.save(user);
	}
}
