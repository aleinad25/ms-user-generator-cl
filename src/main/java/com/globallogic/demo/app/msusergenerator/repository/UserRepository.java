package com.globallogic.demo.app.msusergenerator.repository;

import com.globallogic.demo.app.msusergenerator.repository.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByName(String name);
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findById(UUID id);
	
}
