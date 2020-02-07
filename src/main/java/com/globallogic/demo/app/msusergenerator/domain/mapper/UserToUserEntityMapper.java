package com.globallogic.demo.app.msusergenerator.domain.mapper;

import com.globallogic.demo.app.msusergenerator.common.Validate;
import com.globallogic.demo.app.msusergenerator.domain.entity.Phone;
import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import com.globallogic.demo.app.msusergenerator.repository.entity.PhoneEntity;
import com.globallogic.demo.app.msusergenerator.repository.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.globallogic.demo.app.msusergenerator.security.Constants.SUPER_SECRET_KEY;

@Component
@Qualifier("UserToUserEntityMapper")
public class UserToUserEntityMapper implements Converter<User, UserEntity> {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserToUserEntityMapper(
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserEntity convert(User user) {
        UserEntity newEntity = new UserEntity();
        newEntity.setName(user.getName());
        Validate.emailValidate(user.getEmail());
        newEntity.setEmail(user.getEmail());
        Validate.passwordValidate(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        newEntity.setPassword(user.getPassword());
        List<PhoneEntity> phoneEntityList=new ArrayList<>();
        for (Phone phone: user.getPhones()){
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setCityCode(phone.getCityCode());
            phoneEntity.setCountryCode(phone.getCountryCode());
            phoneEntity.setNumber(phone.getNumber());
            phoneEntityList.add(phoneEntity);
        }
        
        newEntity.setToken(generateToken(user));
        newEntity.setPhones(phoneEntityList);
        newEntity.setActive(true);
        return newEntity;
    }

    public String generateToken(User user) {
        final Instant now = Instant.now();
        return Jwts.builder()
            .setSubject(user.getName())
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
            .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(SUPER_SECRET_KEY))
            .compact();
    }
}
