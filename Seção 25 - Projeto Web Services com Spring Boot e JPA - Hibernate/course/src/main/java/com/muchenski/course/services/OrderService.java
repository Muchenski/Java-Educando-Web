package com.muchenski.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muchenski.course.domain.Order;
import com.muchenski.course.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public Order findById(Long id) {
		Optional<Order> order = repository.findById(id);
		return order.get();
	}

	public List<Order> findAll() {
		List<Order> orders = repository.findAll();
		return orders;
	}
}
