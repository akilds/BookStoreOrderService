package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookstore.dto.OrderServiceDTO;
import com.bookstore.exception.OrderServiceException;
import com.bookstore.model.OrderServiceData;
import com.bookstore.repository.OrderServiceRepository;
import com.bookstore.util.Response;
import com.bookstore.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderSService implements IOrderSService{

	@Autowired
	private OrderServiceRepository orderRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private RestTemplate restTemplate;

	//Returns all the orders 
	public List<OrderServiceData> getAllOrders(String userToken) {
		int userId = tokenUtil.decodeToken(userToken);
		String uri = "http://bookstore-user/user/verifyuserid/" + userId;
		boolean isIdPresent = restTemplate.getForObject(uri, Boolean.class);
		if(isIdPresent) {
			log.info("Get All Orders");
			List<OrderServiceData> getAllNotes = orderRepository.findAll();
			return getAllNotes;
		}else {
			log.error("User Is Not Present");
			throw new OrderServiceException(400, "User Is Not Present");			
		}	
	}

	//Returns all the orders for user
	@Override
	public List<OrderServiceData> getAllOrdersForUser(String userToken) {
		int userId = tokenUtil.decodeToken(userToken);
		String uri = "http://bookstore-user/user/verifyuserid/" + userId;
		boolean isIdPresent = restTemplate.getForObject(uri, Boolean.class);
		if(isIdPresent) {
			log.info("Get All Orders For User");
			List<OrderServiceData> getAllNotes = orderRepository.findAllByUserId(userId);
			return getAllNotes;
		}else {
			log.error("User Is Not Present");
			throw new OrderServiceException(400, "User Is Not Present");			
		}	
	}

	//Places an Order
	@Override
	public Response placeOrder(String userToken, int bookId, OrderServiceDTO orderDTO) {
		int userId = tokenUtil.decodeToken(userToken);
		String uri = "http://bookstore-user/user/verifyuserid/" + userId;
		boolean isUserIdPresent = restTemplate.getForObject(uri, Boolean.class);
		if(isUserIdPresent) {
			String uriB = "http://bookstore-controller/book/verifybookid/" + bookId;
			boolean isBookIdPresent = restTemplate.getForObject(uriB, Boolean.class);
			if(isBookIdPresent) {
				log.info("Place Order");
				OrderServiceData order = modelmapper.map(orderDTO, OrderServiceData.class);
				order.setUserId(userId);
				order.setBookId(bookId);
				orderRepository.save(order);
				return new Response(200, "Added To Cart Successfully", order.getOrderId());
			}else {
				log.error("Book Doesnt Exist");
				throw new OrderServiceException(400, "Book Doesnt Exist");
			}
		}else {
			log.error("User Token Is Invalid");
			throw new OrderServiceException(400, "User Token Is Invalid");
		}
	}

	//Cancels an order
	@Override
	public Response cancelOrder(int orderId, String userToken) {
		Optional<OrderServiceData> isPresent = orderRepository.findById(orderId);
		int userId = tokenUtil.decodeToken(userToken);
		String uri = "http://bookstore-user/user/verifyuserid/" + userId;
		boolean isIdPresent = restTemplate.getForObject(uri, Boolean.class);
		if(isIdPresent && isPresent.isPresent()) {
			log.info("Cancel Order");
			orderRepository.delete(isPresent.get());
			return new Response(200, "Order Canceled Successfully", orderId);
		}else {
			log.error("User/CartId Is Not Present");
			throw new OrderServiceException(400, "User/CartId Is Not Present");
		}
	}

}
