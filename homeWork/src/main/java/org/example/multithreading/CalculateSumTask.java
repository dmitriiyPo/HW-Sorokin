package org.example.multithreading;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class CalculateSumTask implements Callable<Integer> {

    private final List<Integer> numbers;
    private final String nameTask;

    public CalculateSumTask(List<Integer> numbers, String nameTask) {
        this.numbers = numbers;
        this.nameTask = nameTask;
    }


    @Override
    public Integer call() throws Exception {

        System.out.println("Имя задачи: " + nameTask + " в потоке: " + Thread.currentThread().getName());

        Thread.sleep(200);

        int sum = 0;
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            sum += num;
        }
        System.out.println(nameTask + " - вычисление завершены. Сумма: " + sum + " на потоке: " + Thread.currentThread().getName());
        return sum;
    }

}
