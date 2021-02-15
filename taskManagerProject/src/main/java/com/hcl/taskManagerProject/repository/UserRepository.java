package com.hcl.taskManagerProject.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.taskManagerProject.entity.UserEntity;
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	boolean existsByUserNameAndPassword(String userName, String password);

	Optional<UserEntity> findByUserName(String userName);

}
