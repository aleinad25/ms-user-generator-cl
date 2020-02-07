package com.globallogic.demo.app.msusergenerator.usecase;

import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserDataResponse;
import com.globallogic.demo.app.msusergenerator.repository.UserRepository;
import com.globallogic.demo.app.msusergenerator.repository.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createDataResponseStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {
	
	@InjectMocks
	CreateUser createUser;
	@Mock
	UserRepository userRepository;
	@Mock
	Converter<User, UserEntity> userEntityMapper;
	@Mock
	Converter<UserEntity,UserDataResponse> userEntityToUserDataResponseMapper;
	@Mock
	User user;
	@BeforeEach
	void setUp() {
		createUser= new CreateUser(userRepository, userEntityMapper,userEntityToUserDataResponseMapper);
	}
	
	@Test
	void create() {
		when(userEntityToUserDataResponseMapper.convert(any())).thenReturn(createDataResponseStub());
	  UserDataResponse userDataResponse = createUser.create(any());
	  assertNotNull(userDataResponse);
	}

	@Test
	void findByEmail() {
		boolean byEmail = createUser.findByEmail(anyString());
		assertEquals(false, byEmail);
	}
}