package org.example.task_testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class OrderServiceTest {

    private OrderRepository orderRepositoryMock;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        this.orderRepositoryMock = mock(OrderRepository.class);
        this.orderService = new OrderService(orderRepositoryMock);
    }


    @Test
    public void processOrderTestPositiveId() {
        Order order = new Order(1, "Milk", 5, 110.50);
        when(orderRepositoryMock.saveOrder(order)).thenReturn(1);

        String result = orderService.processOrder(order);

        assertEquals("Order processed successfully", result);

        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }


    @Test
    public void processOrderTestNegativeId() {
        Order order = new Order(-2, "Apple", 3, 50.50);
        when(orderRepositoryMock.saveOrder(order)).thenReturn(-2);

        String result = orderService.processOrder(order);

        assertEquals("Order processing failed", result);

        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }


    @Test
    public void processOrderTestZeroId() {
        Order order = new Order(2, "Turkey", 7, 320.20);
        when(orderRepositoryMock.saveOrder(order)).thenReturn(0);

        String result = orderService.processOrder(order);

        assertEquals("Order processing failed", result);

        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }


    @Test
    public void processOrderTestOrderNull() {
        Order order = null;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            orderService.processOrder(order);
        });

        assertEquals("Ошибка, заказ не задан.", exception.getMessage());

        verify(orderRepositoryMock, times(0)).saveOrder(order);
    }


    @Test
    public void processOrderTestException() {
        Order order = new Order(3, "Apple", 5, 110.50);

        when(orderRepositoryMock.saveOrder(order)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.processOrder(order);
        });

        assertEquals("Database error", exception.getMessage());

        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }


    @Test
    public void calculateTotalTestPositiveId() {
        Order order = new Order(1, "Milk", 3, 100.0);

        when(orderRepositoryMock.getOrderById(order.getId())).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(order.getId());

        assertEquals(300, result);

        verify(orderRepositoryMock, times(1)).getOrderById(order.getId());
    }


    @Test
    public void calculateTotalTestNegativeId() {
        int orderId = -1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.calculateTotal(orderId);
        });

        assertEquals("Ошибка, id <= 0 не существует.", exception.getMessage());

        verify(orderRepositoryMock, times(0)).getOrderById(orderId);
    }


    @Test
    public void calculateTotalTestIsEmpty() {
        when(orderRepositoryMock.getOrderById(99)).thenReturn(Optional.empty());

        double result = orderService.calculateTotal(99);

        assertEquals(0, result);
        verify(orderRepositoryMock, times(1)).getOrderById(99);
    }


    @Test
    public void calculateTotalTestQuantityZero() {
       Order order = new Order(1, "Milk", 0, 100.0);
       when(orderRepositoryMock.getOrderById(order.getId())).thenReturn(Optional.of(order));

       double result = orderService.calculateTotal(order.getId());

       assertEquals(0, result);

       verify(orderRepositoryMock, times(1)).getOrderById(order.getId());
    }


    @Test
    public void calculateTotalTestUnitPriceZero() {
        Order order = new Order(2, "Монитор", 4, 0.0);
        when(orderRepositoryMock.getOrderById(order.getId())).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(order.getId());

        assertEquals(0.0, result);
        verify(orderRepositoryMock, times(1)).getOrderById(order.getId());
    }

}
