package com.bookstore.service;

import java.util.List;

import com.bookstore.dto.OrderServiceDTO;
import com.bookstore.model.OrderServiceData;
import com.bookstore.util.Response;

public interface IOrderSService {

	List<OrderServiceData> getAllOrders(String userToken);

	List<OrderServiceData> getAllOrdersForUser(String userToken);

	Response placeOrder(String userToken, int bookId, OrderServiceDTO orderDTO);

	Response cancelOrder(int orderId, String userToken);

}
