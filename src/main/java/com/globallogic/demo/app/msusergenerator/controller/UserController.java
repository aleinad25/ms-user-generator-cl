package com.globallogic.demo.app.msusergenerator.controller;

import com.globallogic.demo.app.msusergenerator.domain.entity.User;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserApiResponse;
import com.globallogic.demo.app.msusergenerator.domain.entity.UserDataResponse;
import com.globallogic.demo.app.msusergenerator.usecase.CreateUser;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final CreateUser createUser;
    
    @Autowired
    public UserController(CreateUser createUser) {
        this.createUser = createUser; 
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<UserDataResponse> createUser(@Valid @RequestBody(required = false) User user){
         if(createUser.findByEmail(user.getEmail())) {
             return new ResponseEntity(new UserApiResponse(false, "El correo ya se encuentra registrado."),
                 HttpStatus.BAD_REQUEST);
        }
        UserDataResponse result = createUser.create(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDataResponse> UpdateUser(@PathVariable("id") String id, @RequestBody User user){
        UserDataResponse userDataResponse = createUser.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(userDataResponse);
    }
}
