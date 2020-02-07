package com.globallogic.demo.app.msusergenerator.usecase;

import com.globallogic.demo.app.msusergenerator.repository.UserRepository;
import com.globallogic.demo.app.msusergenerator.repository.entity.UserEntity;
import java.util.Collections;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserDetailsService {
	private final UserRepository userRepository;

	public UserServicesImpl(
			UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		UserEntity userEntity= userRepository.findByName(name);
		if(userEntity == null){
			 throw new UsernameNotFoundException(name);
		}
		return new User(userEntity.getName(), userEntity.getPassword(), Collections.emptyList());
	}
}
