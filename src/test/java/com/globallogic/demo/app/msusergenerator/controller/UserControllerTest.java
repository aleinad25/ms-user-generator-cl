package com.globallogic.demo.app.msusergenerator.controller;

import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserDataResponse;
import com.globallogic.demo.app.msusergenerator.usecase.CreateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createDataResponseStub;
import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createUserStub;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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