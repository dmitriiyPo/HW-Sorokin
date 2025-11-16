package org.example.task_streamApi;

import java.util.Objects;
import java.util.Set;

public class Customer {
    private final Long id;
    private final String name;
    private final Long level;
    private final Set<Order> orders;

    public Customer(Long id, String name, Long level, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getLevel() {
        return level;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", level=" + level + ", orders=" + orders + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
