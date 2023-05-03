package com.teste.demo.repositories;

import com.teste.demo.entities.OrderItem;
import com.teste.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
