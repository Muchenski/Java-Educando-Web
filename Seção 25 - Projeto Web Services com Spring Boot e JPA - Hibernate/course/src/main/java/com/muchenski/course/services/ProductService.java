package com.muchenski.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muchenski.course.domain.Product;
import com.muchenski.course.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product findById(Long id) {
		Optional<Product> product = repository.findById(id);
		return product.get();
	}

	public List<Product> findAll() {
		List<Product> products = repository.findAll();
		return products;
	}
}
