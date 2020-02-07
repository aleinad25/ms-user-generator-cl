package com.globallogic.demo.app.msusergenerator.usecase;

import com.globallogic.demo.app.msusergenerator.domain.entity.UserDataResponse;
import com.globallogic.demo.app.msusergenerator.domain.mapper.UserEntityToUserDataResponseMapper;
import com.globallogic.demo.app.msusergenerator.domain.mapper.UserToUserEntityMapper;
import com.globallogic.demo.app.msusergenerator.repository.UserRepository;
import com.globallogic.demo.app.msusergenerator.repository.entity.UserEntity;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createDataResponseStub;
import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createUserEntity;
import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createUserEntityExpected;
import static com.globallogic.demo.app.msusergenerator.stub.stubUser.createUserStub;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreateUserTest {
	@InjectMocks
	CreateUser createUser;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	UserToUserEntityMapper userToUserEntityMapper;
	@Mock
	UserEntityToUserDataResponseMapper userEntityToUserDataResponseMapper;
	
	@BeforeEach
	void setUp() {
		createUser= new CreateUser(userRepository, userToUserEntityMapper,userEntityToUserDataResponseMapper);
	}
	

	@Test
	void create() {
		when(userToUserEntityMapper.convert(createUserStub())).thenReturn(createUserEntity());
		when(userRepository.save(createUserEntity())).thenReturn(createUserEntityExpected());
		when(userEntityToUserDataResponseMapper.convert(createUserEntity())).thenReturn(createDataResponseStub());
	  UserDataResponse userDataResponse = createUser.create(createUserStub());
	  assertNotNull(userDataResponse);
	}

	@Test
	void findByEmail() {
		when(userToUserEntityMapper.convert(createUserStub())).thenReturn(createUserEntity());
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

		boolean byEmail = createUser.findByEmail(anyString());
		
	}
}