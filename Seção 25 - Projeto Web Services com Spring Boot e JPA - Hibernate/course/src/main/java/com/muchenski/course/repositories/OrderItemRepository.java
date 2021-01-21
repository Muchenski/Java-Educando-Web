package com.muchenski.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muchenski.course.domain.OrderItem;
import com.muchenski.course.domain.OrderItemPk;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {

}
