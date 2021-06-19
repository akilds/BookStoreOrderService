package com.bookstore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "order_service")
public @Data class OrderServiceData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private int orderId;
	
	@Column
	private int userId;
	private int bookId;
	private long quantity;
	private double price;
	private String address;
	private LocalDateTime orderDate = LocalDateTime.now();
}
