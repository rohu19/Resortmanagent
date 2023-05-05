package com.innovator.resort.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.innovator.resort.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
