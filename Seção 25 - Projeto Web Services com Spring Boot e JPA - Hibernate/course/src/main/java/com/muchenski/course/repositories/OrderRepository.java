package com.muchenski.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muchenski.course.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
