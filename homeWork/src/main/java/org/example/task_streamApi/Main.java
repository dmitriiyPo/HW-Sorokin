package org.example.task_streamApi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Product p1 = new Product(1L, "История", "Books", new BigDecimal("150.99"));
        Product p2 = new Product(2L, "Энциклопедия", "Books", new BigDecimal("200.20"));
        Product p3 = new Product(3L, "Рыбалка", "Books", new BigDecimal("110.00"));
        Product p4 = new Product(4L, "Авто", "Books", new BigDecimal("90.00"));
        Product p5 = new Product(5L, "Замки", "Books", new BigDecimal("95.50"));
        Product p6 = new Product(6L, "Кровать", "Children's products", new BigDecimal("5000.70"));
        Product p7 = new Product(7L, "Стол", "Children's products", new BigDecimal("6000.20"));
        Product p8 = new Product(8L, "Пеленка", "Children's products", new BigDecimal("100.40"));
        Product p9 = new Product(9L, "Бутылка", "Children's products", new BigDecimal("90.60"));
        Product p10 = new Product(10L, "Чепчик", "Children's products", new BigDecimal("120.30"));
        Product p11 = new Product(11L, "Машина", "Toys", new BigDecimal("1500.90"));
        Product p12 = new Product(12L, "Юла", "Toys", new BigDecimal("900.50"));
        Product p13 = new Product(13L, "Поезд", "Toys", new BigDecimal("2500.50"));
        Product p14 = new Product(14L, "Самосвал", "Toys", new BigDecimal("1300.20"));
        Product p15 = new Product(15L, "Пазлы", "Toys", new BigDecimal("600.30"));

        Order o1 = new Order(101L, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 2, 10),"Delivered",
                new HashSet<>(Arrays.asList(p1, p4)));

        Order o2 = new Order(102L, LocalDate.of(2021, 2, 6), LocalDate.of(2021, 2, 15),"Delivered",
                new HashSet<>(Arrays.asList(p2, p3, p6)));

        Order o3 = new Order(103L, LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 25),"Processing",
                new HashSet<>(Arrays.asList(p5, p8)));

        Order o4 = new Order(104L, LocalDate.of(2021, 2, 17), LocalDate.of(2021, 2, 26),"Delivered",
                new HashSet<>(Arrays.asList(p6, p7, p12)));

        Order o5 = new Order(105L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 20),"Delivered",
                new HashSet<>(List.of(p10)));

        Order o6 = new Order(106L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 21),"Delivered",
                new HashSet<>(Arrays.asList(p9, p11, p12)));

        Order o7 = new Order(107L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 29),"Processing",
                new HashSet<>(Arrays.asList(p1, p13, p15)));

        Order o8 = new Order(108L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 29),"Delivered",
                new HashSet<>(Arrays.asList(p10, p12, p14)));

        Order o9 = new Order(109L, LocalDate.of(2021, 3, 21), LocalDate.of(2021, 3, 30),"Delivered",
                new HashSet<>(Arrays.asList(p2, p4, p7, p8)));

        Order o10 = new Order(110L, LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 6),"Processing",
                new HashSet<>(Arrays.asList(p9, p10, p13)));

        Customer c1 = new Customer(11L, "Dima", 1L, new HashSet<>(Arrays.asList(o1, o2, o3, o4, o5)));
        Customer c2 = new Customer(22L, "Kate", 2L, new HashSet<>(Arrays.asList(o6, o7, o8, o9, o10)));
        Customer c3 = new Customer(33L, "Danila", 4L, new HashSet<>(Arrays.asList(o1, o7, o3, o4, o8)));
        Customer c4 = new Customer(44L, "Anna", 3L, new HashSet<>(Arrays.asList(o3, o4, o6, o8, o10)));
        Customer c5 = new Customer(55L, "Artem", 5L, new HashSet<>(Arrays.asList(o2, o5, o6, o9, o10)));

        List<Customer> customers = Arrays.asList(c1, c2, c3, c4, c5);


        //Задание 1 (Получите список продуктов из категории "Books" с ценой более 100.)
        List<Product> categoryOver100 = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Books")
                        && product.getPrice().compareTo(new BigDecimal("100")) > 0)
                .distinct()
                .toList();

        System.out.println("\nЗадание 1: Получите список продуктов из категории \"Books\" с ценой более 100.");
        categoryOver100.forEach(System.out::println);


        //Задание 2 (Получите список заказов с продуктами из категории "Children's products".)
        List<Order> orderByCategory = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> product.getCategory().equals("Children's products")))
                .distinct()
                .toList();

        System.out.println("\nЗадание 2: Получите список заказов с продуктами из категории \"Children's products\".");
        orderByCategory.forEach(System.out::println);


        //Задание 3 (Получите список продуктов из категории "Toys" и примените скидку 10% и получите сумму всех
        //продуктов.)
        BigDecimal totalPriceProductsWithDiscount = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Toys"))
                .distinct()
                .map(product -> product.getPrice().multiply(new BigDecimal("0.9")))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nЗадание 3: Получите список продуктов из категории \"Toys\" и примените скидку 10% и получите сумму всех продуктов");
        System.out.println("Общая сумма: " + totalPriceProductsWithDiscount);


        //Задание 4 (Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.)
        List<Product> twoLvl = customers.stream()
                .filter(customer -> customer.getLevel() == 2)
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isAfter(LocalDate.of(2021, 2, 1))
                        && order.getOrderDate().isBefore(LocalDate.of(2021, 4, 1)))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();

        System.out.println("\nЗадание 4: Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.");
        twoLvl.forEach(System.out::println);


        //Задание 5 (Получите топ 2 самые дешевые продукты из категории "Books".)
        List<Product> top2LooPrice = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Books"))
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .toList();

        System.out.println("\nЗадание 5: Получите топ 2 самые дешевые продукты из категории \"Books\".");
        top2LooPrice.forEach(System.out::println);


        //Задание 6 (Получите 3 самых последних сделанных заказа.)
        List<Order> lastThreeOrder = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .distinct()
                .limit(3)
                .toList();

        System.out.println("\nЗадание 6: Получите 3 самых последних сделанных заказа.");
        lastThreeOrder.forEach(System.out::println);


        //Задание 7 (Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните
        //список их продуктов.)

        System.out.println("\nЗадание 7: Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните\n" +
                "список их продуктов.");

        List<Product> idOrderWithListProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
                .peek(order -> System.out.println("Order id: " + order.getId()))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();

        idOrderWithListProduct.forEach(System.out::println);


        //Задание 8 (Рассчитайте общую сумму всех заказов, сделанных в феврале 2021.)
        BigDecimal totalPriceOrderByDate = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().getYear() == 2021 && order.getOrderDate().getMonthValue() == 2)
                .map(order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nЗадание 8: Рассчитайте общую сумму всех заказов, сделанных в феврале 2021." + "\nОбщая сумма: " + totalPriceOrderByDate);


        //Задание 9 (Рассчитайте средний платеж по заказам, сделанным 14-марта-2021.)
        double avgDipositOrderByDate = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3,14)))
                .distinct()
                .map(order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))

                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);

        System.out.println("\nЗадание 9: Рассчитайте средний платеж по заказам, сделанным 14-марта-2021." + "\nСредний платеж: " + avgDipositOrderByDate);


        //Задание 10 (Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех
        //продуктов категории "Книги".)
        System.out.println("\nЗадание 10: Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех\n" +
                "продуктов категории \"Книги\".");

        List<Product> statData = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Books"))
                .distinct()
                .toList();


        BigDecimal summaStat = statData.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Сумма: " + summaStat);


        double avgStat = statData.stream()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .average()
                .orElse(0.0);
        System.out.println("Среднее: " + avgStat);


        Optional<BigDecimal> maxStat = statData.stream()
                .map(Product::getPrice)
                .max(Comparator.comparing(BigDecimal::doubleValue));
        System.out.print("Максимальная цена: ");
        maxStat.ifPresent(System.out::println);


        Optional<BigDecimal> minStat = statData.stream()
                .map(Product::getPrice)
                .min(Comparator.comparing(BigDecimal::doubleValue));
        System.out.print("Минимальная цена цена: ");
        minStat.ifPresent(System.out::println);


        long sizeProductByCategory = statData.size();
        System.out.println("Кол-во продуктов: " + sizeProductByCategory);


        //Задание 11 (Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе)
        Map<Long, Integer> dateKetAndValue = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(Order::getId, order -> order.getProducts().size()));

        System.out.println("\nЗадание 11: Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе");
        dateKetAndValue.forEach((id, count) -> System.out.println("Order id: " + id + " -> " + count));


        //Задание 12 (Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов)
        Map<Customer, List<Order>> keyAndListOrder = customers.stream()
                .collect(Collectors.toMap(
                        customer -> customer,
                        customer -> new ArrayList<>(customer.getOrders())
                ));
        System.out.println("\nЗадание 12: Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов");
        keyAndListOrder.forEach((customer, list) -> System.out.println("customer: " + customer + " -> " + list));


        //Задание 13 (Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа.)
        Map<Order, Double> keyOrderValueSum = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(
                        order -> order,
                        order -> order.getProducts().stream()
                                .map(Product::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .doubleValue()
                ));

        System.out.println("\nЗадание 13: Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа.");
        keyOrderValueSum.forEach((order, sum) -> System.out.println("Order: " + order + " sum: " + sum));


        //Задание 14 (Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории)
        Map<String, List<String>> keyCategoryValueList = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                       Collectors.mapping(Product::getName, Collectors.toList())
                ));

        System.out.println("\nЗадание 14: Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории");
        keyCategoryValueList.forEach((category, list) -> System.out.println("Category: " + category + " Список названий товаров в категории: " + list));


        //Задание 15 (Получите Map<String, Product> → самый дорогой продукт по каждой категории.)
        Map<String, Product> topRichProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)),
                                opt -> opt.orElse(null)
                        )
                ));

        System.out.println("\nЗадание 15: Получите Map<String, Product> → самый дорогой продукт по каждой категории.");
        topRichProduct.forEach((category, product) -> System.out.println("Category: " + category + " Product: " + product));

    }
}
