package com.globallogic.demo.app.msusergenerator.stub;

import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserDataResponse;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserInfo;
import com.globallogic.demo.app.msusergenerator.repository.entity.UserEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Matchers.anyString;

public class stubUser {
	public static UserDataResponse createDataResponseStub() {
		UserDataResponse userDataResponse= new UserDataResponse();
		User user= new User();
		user.setName("dani");
		user.setPassword("plono23P");
		user.setEmail("dani@gmail.com");
		UserInfo userInfo = new UserInfo();
		userInfo.setToken("defdfjnwkjanwejkefwefef");
		userInfo.setId(UUID.randomUUID().toString());
		userInfo.setActive(true);

		userDataResponse.setUser(user);
		userDataResponse.setUserInfo(userInfo);
		return userDataResponse;
	}

	public static User createUserStub() {
		User user = new User();
		user.setName("dani");
		user.setPassword("plono23P");
		user.setEmail("dani@gmail.com");
		return user;
	}
	
	public static UserEntity createUserEntity(){
		UserEntity newEntity = new UserEntity();
		newEntity.setName("dani");
		newEntity.setPassword("plono23P");
		newEntity.setEmail("dani@gmail.com");
		newEntity.setToken("sfsdfdsfsdfdsf");
		newEntity.setActive(true);
		newEntity.setCreatedDate(LocalDateTime.now());
		return newEntity;
	}
	
	public static UserEntity createUserEntityExpected(){
		UserEntity userEntity= new UserEntity();
		userEntity.setEmail(anyString());
		userEntity.setName("dani");
		userEntity.setPassword("dani@gmail.com");
		userEntity.setId(UUID.randomUUID());
		userEntity.setActive(true);
		userEntity.setToken("JWT");
		return userEntity;
	}
}
