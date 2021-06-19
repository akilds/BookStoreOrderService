package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dto.OrderServiceDTO;
import com.bookstore.model.OrderServiceData;
import com.bookstore.service.IOrderSService;
import com.bookstore.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("orderservice")
@Slf4j
public class OrderServiceController {

	@Autowired
	private IOrderSService orderService;
	
	@GetMapping("/getallorders")
	public ResponseEntity<List<?>> getAllOrders(@RequestHeader String userToken) {
		log.info("Get All Orders");
		List<OrderServiceData> response = orderService.getAllOrders(userToken);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getallordersforuser")
	public ResponseEntity<List<?>> getAllOrdersForUser(@RequestHeader String userToken) {
		log.info("Get All Orders For User");
		List<OrderServiceData> response = orderService.getAllOrdersForUser(userToken);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/placeorder")
	public ResponseEntity<Response> placeOrder(@RequestHeader String userToken,
											   @RequestHeader int bookId,
											   @RequestBody OrderServiceDTO orderDTO) {
		log.info("Place Order");
		Response response = orderService.placeOrder(userToken,bookId,orderDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/cancelorder")
	public ResponseEntity<Response> cancelOrder(@RequestParam int orderId,
											   	@RequestHeader String userToken) {
		log.info("Cancel Order");
		Response response  = orderService.cancelOrder(orderId,userToken);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
