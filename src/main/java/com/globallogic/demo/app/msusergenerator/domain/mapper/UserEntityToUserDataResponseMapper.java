package com.globallogic.demo.app.msusergenerator.domain.mapper;

import com.globallogic.demo.app.msusergenerator.domain.entity.Phone;
import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserDataResponse;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserInfo;
import com.globallogic.demo.app.msusergenerator.repository.entity.PhoneEntity;
import com.globallogic.demo.app.msusergenerator.repository.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Qualifier("UserEntityToUserDataResponseMapper")
public class UserEntityToUserDataResponseMapper implements Converter<UserEntity, UserDataResponse> {
    @Override
    public UserDataResponse convert(UserEntity source) {
        UserDataResponse userDataResponse= new UserDataResponse();
        User user= new User();
        user.setName(source.getName());
        user.setEmail(source.getEmail());
        user.setPassword(source.getPassword());
        List<Phone> phoneList=new ArrayList<>();
        Phone phone= new Phone();
        for (PhoneEntity phoneIndex: source.getPhones()) {
            phone.setNumber(phoneIndex.getNumber());
            phone.setCityCode(phoneIndex.getCityCode());
            phone.setCountryCode(phoneIndex.getCountryCode());
            phoneList.add(phone);
        }
        user.setPhones(phoneList);
        userDataResponse.setUser(user);
        UserInfo userInfo= new UserInfo();
        
        userInfo.setId(source.getId().toString());
        userInfo.setCreatedDate(source.getCreatedDate().toLocalDate());
        userInfo.setModifyDate(source.getModifyDate().toLocalDate());
        userInfo.setLastLoginDate(source.getLastLoginDate().toLocalDate());
        userInfo.setToken(source.getToken());
        userInfo.setActive(source.getActive());
        userDataResponse.setUserInfo(userInfo);
        return userDataResponse;
    }
}
