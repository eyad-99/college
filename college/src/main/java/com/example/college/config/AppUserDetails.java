package com.example.college.config;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.college.dao.UserRepository;
import com.example.college.entity.User;
import com.example.college.services.UserService;



@Service
public class AppUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository uRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<User> users = uRepository.findByEmail(email);
		if (users.size() == 0) {
			throw new UsernameNotFoundException("User details not found for the user "+email);
		}
		return new UserService(users.get(0));
	}
	
	
	
}