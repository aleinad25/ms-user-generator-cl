package com.globallogic.demo.app.msusergenerator.controller;

import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserDataResponse;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserInfo;
import com.globallogic.demo.app.msusergenerator.usecase.CreateUser;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createDataResponseStub;
import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createUserStub;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

	@Mock
	CreateUser createUser;
	
	@InjectMocks
	UserController userController;
	

	@BeforeEach
	void setUp(){
		userController= new UserController(createUser);
	}
	@Test
	void createUser(){
		User user = createUserStub();
		when(createUser.create(user)).thenReturn(createDataResponseStub());
		ResponseEntity<UserDataResponse> user1 = userController.createUser(user);
		assertNotNull(user1);
	}

}