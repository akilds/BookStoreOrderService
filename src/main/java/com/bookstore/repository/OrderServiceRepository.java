package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.model.OrderServiceData;

public interface OrderServiceRepository extends JpaRepository<OrderServiceData, Integer>{

	@Query(value = "select * from order_data where user_id = :userId", nativeQuery = true)
	List<OrderServiceData> findAllByUserId(int userId);

}
