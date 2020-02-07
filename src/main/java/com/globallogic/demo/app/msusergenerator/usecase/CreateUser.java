package com.globallogic.demo.app.msusergenerator.usecase;

import com.globallogic.demo.app.msusergenerator.common.NotFoundException;
import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserDataResponse;
import com.globallogic.demo.app.msusergenerator.repository.UserRepository;
import com.globallogic.demo.app.msusergenerator.repository.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {

    private final UserRepository userRepository;
    @Qualifier(value = "UserToUserEntityMapper")
    private final Converter<User,UserEntity> userEntityMapper;
    @Qualifier(value = "UserEntityToUserDataResponseMapper")
    private final Converter<UserEntity,UserDataResponse> userResponseMapper;

    @Autowired
    public CreateUser(UserRepository userRepository, Converter<User, UserEntity> userEntityMapper, Converter<UserEntity, UserDataResponse> userResponseMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.userResponseMapper = userResponseMapper;
    }

    public UserDataResponse create(User user){
        UserEntity userEntity = userEntityMapper.convert(user);
        userEntity = userRepository.save(userEntity);
        return userResponseMapper.convert(userEntity);
    }
    

    public boolean findByEmail(String email){
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        return  byEmail.isPresent();
    }

    public UserDataResponse updateUser(String id, User user) {
        Optional<UserEntity> idEntity = userRepository.findById(UUID.fromString((id)));
        if (idEntity.isPresent()) {
            UserEntity userEntity = userEntityMapper.convert(user);
            userEntity = userRepository.save(userEntity);
            return userResponseMapper.convert(userEntity);
        } else {
            throw new NotFoundException(id);
        }
    }
}
