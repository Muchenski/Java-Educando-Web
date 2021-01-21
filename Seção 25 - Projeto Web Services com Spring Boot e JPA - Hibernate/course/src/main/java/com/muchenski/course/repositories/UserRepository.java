package com.muchenski.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muchenski.course.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
