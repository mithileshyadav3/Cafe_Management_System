package com.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

}
