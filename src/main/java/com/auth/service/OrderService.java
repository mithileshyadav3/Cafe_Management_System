package com.auth.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.DTO.OrderItemRequest;
import com.auth.DTO.OrderRequest;
import com.auth.model.Order;
import com.auth.model.OrderItem;
import com.auth.model.Product;
import com.auth.model.Users;
import com.auth.repo.OrderRepo;
import com.auth.repo.ProductRepo;

@Service
public class OrderService {
	@Autowired
	private OrderRepo orderRepo;
@Autowired
private ProductRepo productRepo;
	public  Order placedOrder(OrderRequest request, Users users) {
		// TODO Auto-generated method stub
		Order order=new Order();
		order.setDate(new Date());
		order.setStatus("Placed");
		order.setUsers(users);
		List<OrderItem>items=new ArrayList<>();
		for(OrderItemRequest r:request.getItems()) {
			Product product=productRepo.findById(r.getProductId()).get();
		
		OrderItem item=new OrderItem();
		item.setProduct(product);
		item.setUnit_price(product.getPrice());
		double price=product.getPrice();
		int quantity=r.getQuantity();
		item.setQuantity(r.getQuantity());
		item.setTotalprice(price*quantity);
		item.setOrder(order);
		items.add(item);
		}
		order.setOrderItems(items);
		return orderRepo.save(order);
	}

}
