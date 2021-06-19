package com.bookstore.dto;

import lombok.Data;
import lombok.ToString;

@Data
public @ToString class OrderServiceDTO {

	public long quantity;
	
	public double price;
	
	public String address;
}
