package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)

        );
        List <Map.Entry<String,Double>> top3ExpensiveProduct = orders.stream()
                .collect(Collectors.groupingBy(Order:: getProduct,
                        Collectors.summingDouble(Order::getCost)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double> comparingByValue().reversed())
                .limit(orders.size())
                .collect(Collectors.toList());

        top3ExpensiveProduct.forEach(entry ->
                System.out.println("Product: " + entry.getKey() + ", Total Value: " + entry.getValue()));
    }
}