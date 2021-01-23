package com.muchenski.course.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.muchenski.course.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
