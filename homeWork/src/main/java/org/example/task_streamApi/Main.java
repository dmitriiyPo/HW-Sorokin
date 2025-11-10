package org.example.task_streamApi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Product p1 = new Product(1L, "Лодка", "Рыбалка", new BigDecimal("15500.99"));
        Product p2 = new Product(2L, "Удочка", "Рыбалка", new BigDecimal("2500.20"));
        Product p3 = new Product(3L, "Сачок", "Рыбалка", new BigDecimal("900.00"));
        Product p4 = new Product(4L, "Масло", "Авто", new BigDecimal("1500.00"));
        Product p5 = new Product(5L, "Колеса", "Авто", new BigDecimal("32600.50"));
        Product p6 = new Product(6L, "Омывайка", "Авто", new BigDecimal("320.70"));
        Product p7 = new Product(7L, "Молоко", "Еда", new BigDecimal("110.20"));
        Product p8 = new Product(8L, "Хлеб", "Еда", new BigDecimal("50.40"));
        Product p9 = new Product(9L, "Зефир", "Еда", new BigDecimal("99.60"));
        Product p10 = new Product(10L, "Молоток", "Инструменты", new BigDecimal("250.30"));
        Product p11 = new Product(11L, "Пила", "Инструменты", new BigDecimal("1500.90"));
        Product p12 = new Product(12L, "Отвертка", "Инструменты", new BigDecimal("70.50"));
        Product p13 = new Product(13L, "Ручка", "Концелярия", new BigDecimal("190.50"));
        Product p14 = new Product(14L, "Карандаш", "Концелярия", new BigDecimal("60.20"));
        Product p15 = new Product(15L, "Резинка", "Концелярия", new BigDecimal("150.30"));

        Order o1 = new Order(101L, LocalDate.of(2025, 4, 12), LocalDate.of(2025, 4, 16),"Delivered",
                new HashSet<>(Arrays.asList(p1, p4)));

        Order o2 = new Order(102L, LocalDate.of(2025, 4, 13), LocalDate.of(2025, 4, 15),"Delivered",
                new HashSet<>(Arrays.asList(p2, p3, p6)));

        Order o3 = new Order(103L, LocalDate.of(2025, 4, 13), LocalDate.of(2025, 4, 19),"Processing",
                new HashSet<>(Arrays.asList(p5, p8)));

        Order o4 = new Order(104L, LocalDate.of(2025, 4, 14), LocalDate.of(2025, 4, 17),"Delivered",
                new HashSet<>(Arrays.asList(p6, p7, p12)));

        Order o5 = new Order(105L, LocalDate.of(2025, 4, 16), LocalDate.of(2025, 4, 21),"Delivered",
                new HashSet<>(List.of(p10)));

        Order o6 = new Order(106L, LocalDate.of(2025, 4, 17), LocalDate.of(2025, 4, 23),"Delivered",
                new HashSet<>(Arrays.asList(p9, p11, p12)));

        Order o7 = new Order(107L, LocalDate.of(2025, 4, 18), LocalDate.of(2025, 4, 25),"Processing",
                new HashSet<>(Arrays.asList(p1, p13, p15)));

        Order o8 = new Order(108L, LocalDate.of(2025, 4, 20), LocalDate.of(2025, 4, 26),"Delivered",
                new HashSet<>(Arrays.asList(p10, p12, p14)));

        Order o9 = new Order(109L, LocalDate.of(2025, 4, 21), LocalDate.of(2025, 4, 29),"Delivered",
                new HashSet<>(Arrays.asList(p2, p4, p7, p8)));

        Order o10 = new Order(110L, LocalDate.of(2025, 4, 23), LocalDate.of(2025, 4, 30),"Processing",
                new HashSet<>(Arrays.asList(p9, p10, p13)));

        Customer c1 = new Customer(11L, "Dima", 1L, new HashSet<>(Arrays.asList(o1, o2, o3, o4, o5)));
        Customer c2 = new Customer(22L, "Kate", 2L, new HashSet<>(Arrays.asList(o6, o7, o8, o9, o10)));
        Customer c3 = new Customer(33L, "Danila", 4L, new HashSet<>(Arrays.asList(o1, o7, o3, o4, o8)));
        Customer c4 = new Customer(44L, "Anna", 3L, new HashSet<>(Arrays.asList(o3, o4, o6, o8, o10)));
        Customer c5 = new Customer(55L, "Artem", 5L, new HashSet<>(Arrays.asList(o2, o5, o6, o9, o10)));

        List<Customer> customers = Arrays.asList(c1, c2, c3, c4, c5);


        //Задание 1
        List<Product> categoryOver100 = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Рыбалка")
                        && product.getPrice().compareTo(new BigDecimal("1000")) > 0)
                .distinct()
                .sorted(Comparator.comparing(Product::getId))
                .toList();

        System.out.println("\nЗадание 1: Получение списка продуктов нужной категории {Рыбалка} с ценной больше 1000");
        categoryOver100.forEach(System.out::println);


        //Задание 2
        List<Order> orderByCategory = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> product.getCategory().equals("Авто")))
                .distinct()
                .sorted(Comparator.comparing(Order::getId))
                .toList();

        System.out.println("\nЗадание 2: Получение списка заказа с нужной категорией {Авто}");
        orderByCategory.forEach(System.out::println);


        //Задание 3
        List<Product> productsDiscount = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Еда"))
                .distinct()
                .map(product -> new Product(
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getPrice().multiply(new BigDecimal("0.9"))
                ))
                .toList();

        BigDecimal totalPrice = productsDiscount.stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nЗадание 3: Получение списка продуктов с нужной категорией {Еда} и приминить скидку 10% и" +
                " получить сумму всех продуктов");
        productsDiscount.forEach(System.out::println);
        System.out.println("Общая сумма: " + totalPrice);


        //Задание 4
        List<Product> twoLvl = customers.stream()
                .filter(customer -> customer.getLevel() == 2)
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> !order.getOrderDate().isBefore(LocalDate.of(2025, 4, 17))
                        && order.getOrderDate().isBefore(LocalDate.of(2025, 4, 30)))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();

        System.out.println("\nЗадание 4: Получить список продуктов, заказанных клиентом второго уровня c 4 апреля по 30 апреля 2025 года");
        twoLvl.forEach(System.out::println);


        //Задание 5
        List<Product> top2LooPrice = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Еда"))
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .toList();

        System.out.println("\nЗадание 5: Получить топ 2 дешевых продукта из категории {Еда}");
        top2LooPrice.forEach(System.out::println);


        //Задание 6
        List<Order> lastThreeOrder = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .distinct()
                .limit(3)
                .toList();

        System.out.println("\nЗадание 6: Получить 3 последних заказа");
        lastThreeOrder.forEach(System.out::println);


        //Задание 7
        List<Product> idOrderAndGetProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2025, 4, 13)))
                .peek(order -> System.out.println(("Order ID: " + order.getId())))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .sorted(Comparator.comparing(Product::getId))
                .toList();

        System.out.println("\nЗадание 7: Получить список заказов, сделаных 13-апрель-2025, выведите id заказов в консоль и затем верните список продуктов");
        idOrderAndGetProduct.forEach(System.out::println);


        //Задание 8 (преобзразуем поток заказов в поток всех продуктов из этих заказов, извлекаем цену каждого продукта
        // и суммируем все ценны
        BigDecimal totalPriceOrderByDate = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().getYear() == 2025 && order.getOrderDate().getMonthValue() == 4)

                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)

                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nЗадание 8: Расчитать общую сумму всех заказов, сделанных в апрель-2025" + "\nОбщая сумма: " + totalPriceOrderByDate);


        //Задание 8 (для каждого заказа создается подпоток продуктов, извлекает их цены и суммирует)
        BigDecimal totalPriceOrderByDate2 = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().getYear() == 2025 && order.getOrderDate().getMonthValue() == 4)

                .map(order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))

                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nЗадание 8: Расчитать общую сумму всех заказов, сделанных в апрель-2025" + "\nОбщая сумма: " + totalPriceOrderByDate2);


        //Задание 9
        List<BigDecimal> avgDipositOrderByDate = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2025, 4,13)))
                .distinct()
                .map(order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .toList();

        double avg21April = avgDipositOrderByDate.stream()
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);

        System.out.println("\nЗадание 9: Расчитать средний платеж по заказам, сделанным 13-апреля-2025" + "\nСредний платеж: " + avg21April);


        //Задание 10
        System.out.println("\nЗадание 10: Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех продуктов категории {Концелярия}");

        List<Product> statData = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Концелярия"))
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
        System.out.print("Кол-во продуктов: " + sizeProductByCategory);


        //Задание 10
        DoubleSummaryStatistics statsBooks = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Концелярия"))
                .distinct()
                .mapToDouble(product -> product.getPrice().doubleValue())
                .summaryStatistics();

        System.out.println("");
        System.out.println("\nЗадание 10: Статистика для продуктов из категории \"Концелярия\":");
        System.out.println("Сумма: " + statsBooks.getSum());
        System.out.println("Среднее: " + statsBooks.getAverage());
        System.out.println("Максимум: " + statsBooks.getMax());
        System.out.println("Минимум: " + statsBooks.getMin());
        System.out.println("Количество: " + statsBooks.getCount());


        //Задание 11
        Map<Long, Integer> dateKetAndValue = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(Order::getId, order -> order.getProducts().size()));

        System.out.println("\nЗадание 11: Получить данные key - id заказа, value - кол-во товаров в заказе:");
        dateKetAndValue.forEach((id, count) -> System.out.println("Order id: " + id + " -> " + count));


        //Задание 12
        Map<Customer, List<Order>> keyAndListOrder = customers.stream()
                .collect(Collectors.toMap(
                        customer -> customer,
                        customer -> new ArrayList<>(customer.getOrders())
                ));
        System.out.println("\nЗадание 12: Создайте key - покупатель, value - список его заказов:");
        keyAndListOrder.forEach((id, list) -> System.out.println("Order id: " + id + " -> " + list));


        //Задание 13
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

        System.out.println("\nЗадание 13: Создайте key - заказ, value - общая сумма продуктов заказа:");
        keyOrderValueSum.forEach((order, sum) -> System.out.println("Order: " + order + " sum: " + sum));


        //Задание 14
        Map<String, List<String>> keyCategoryValueList = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                       Collectors.mapping(Product::getName, Collectors.toList())
                ));

        System.out.println("\nЗадание 14: Получите key - категория, value - список названий товаров в категории:");
        keyCategoryValueList.forEach((category, list) -> System.out.println("Category: " + category + " Список названий товаров в категории: " + list));


        //Задание 15
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

        System.out.println("\nЗадание 15: Получите самый дорогой продукт по каждой категории:");
        topRichProduct.forEach((category, product) -> System.out.println("Category: " + category + " Product: " + product));

    }
}
