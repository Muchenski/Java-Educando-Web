package com.muchenski.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muchenski.course.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
