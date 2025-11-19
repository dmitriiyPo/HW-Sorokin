package org.example.task_testing;

import java.util.Optional;

public class OrderService {

   private final OrderRepository orderRepository;

   public OrderService(OrderRepository orderRepository) {
       this.orderRepository = orderRepository;
   }


   public String processOrder(Order order) {
       if (order == null) throw new NullPointerException("Ошибка, заказ не задан.");
       int id = orderRepository.saveOrder(order);
       return (id > 0) ? "Order processed successfully" : "Order processing failed";
   }


   public double calculateTotal(int id) {
       if (id <= 0) throw new IllegalArgumentException("Ошибка, id <= 0 не существует.");
       Optional<Order> order = orderRepository.getOrderById(id);
       return order.map(Order::totalPrice).orElse(0.0);
   }

}
