package com.lesson.shoplist.security;

import com.lesson.shoplist.persist.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthService implements UserDetailsService {

	private final UserRepo repo;

	@Autowired
	public UserAuthService(UserRepo repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return repo.findByUsername(username)
				 .map(user -> new User(
						 user.getUsername(),
						 user.getPassword(),
						 Collections.singletonList(new SimpleGrantedAuthority("USER"))
				 ))
				 .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
	}
}
