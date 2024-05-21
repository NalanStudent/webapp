package com.dbproject.webapp.Services;

import com.dbproject.webapp.Model.CartItem;
import com.dbproject.webapp.Model.Order;
import com.dbproject.webapp.Model.OrderItem;
import com.dbproject.webapp.Model.User;
import com.dbproject.webapp.Repository.CartItemRepository;
import com.dbproject.webapp.Repository.OrderItemRepository;
import com.dbproject.webapp.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order placeOrder(User user) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(user.getId());
        Order order = new Order();
        order.setUser(user);
        order.setStatus("Processing");
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            order.getOrderItems().add(orderItem);
            total = total.add(orderItem.getPrice());
        }

        order.setTotal(total);
        order = orderRepository.save(order);

        cartItemRepository.deleteAll(cartItems);

        return order;
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUserId(user.getId());
    }
}