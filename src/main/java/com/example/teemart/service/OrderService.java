/*
 * package com.example.teemart.service;
 * 
 * import java.time.LocalDateTime; import java.util.ArrayList; import
 * java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.example.teemart.entity.CartItem; import
 * com.example.teemart.entity.Order; import
 * com.example.teemart.entity.OrderItem; import com.example.teemart.entity.User;
 * import com.example.teemart.repository.CartRepo; import
 * com.example.teemart.repository.OrderItemRepo; import
 * com.example.teemart.repository.OrderRepo;
 * 
 * ///@Service public class OrderService {
 * 
 * @Autowired private OrderRepo orderrepo;
 * 
 * ///@Autowired ///private OrderItemRepo orderitemrepo;
 * 
 * @Autowired private CartRepo cartitemrepo;
 * 
 * public boolean placeOrder(User user) {
 * 
 * List<CartItem> cartItems = cartitemrepo.findByUser(user);
 * 
 * if (cartItems.isEmpty()) { return false; }
 * 
 * Order order = new Order(); order.setUser(user);
 * order.setOrderdate(LocalDateTime.now()); order.setStatus("PLACED");
 * 
 * List<OrderItem> orderItems = new ArrayList<OrderItem>(); double totalAmount =
 * 0;
 * 
 * for (CartItem cartItem : cartItems) { OrderItem item = new OrderItem();
 * item.setTshirt(cartItem.getTshirt());
 * item.setQuantity(cartItem.getQuantity());
 * item.setPrice(cartItem.getTshirt().getPrice()); item.setOrder(order);
 * 
 * orderItems.add(item); totalAmount += cartItem.getQuantity() *
 * cartItem.getTshirt().getPrice(); }
 * 
 * order.setOrderitems(orderItems); order.setTotalamount(totalAmount);
 * orderrepo.save(order);
 * 
 * cartitemrepo.deleteAll(cartItems); // clear cart
 * 
 * return true; // show order }
 * 
 * 
 * 
 * 
 * }
 */