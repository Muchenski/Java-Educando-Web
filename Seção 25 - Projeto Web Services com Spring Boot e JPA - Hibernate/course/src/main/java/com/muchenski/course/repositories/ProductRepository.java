package com.muchenski.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muchenski.course.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
