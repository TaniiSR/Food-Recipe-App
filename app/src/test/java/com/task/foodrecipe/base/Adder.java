package com.task.foodrecipe.base;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Adder {
    public static int sum(List<Callable<Integer>> functions,
                          Consumer<Integer> onSumChanged) throws Exception {
        int sum = 0;
        for (int i = 0; i < functions.size(); i++) {
            sum += functions.get(i).call().intValue();
            onSumChanged.accept(sum);
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        Callable<Integer> expensiveFunction = () -> (int) IntStream.range(0, 200000000).count();
        Callable<Integer> cheapFunction = () -> (int) IntStream.range(0, 10000000).count();
        List<Callable<Integer>> functionList = Arrays.asList(expensiveFunction, cheapFunction);
        Consumer<Integer> onSumChanged = (sum) -> System.out.println("Current result: " + sum);

        // Computationally expensive functions need more time than cheaper functions.
        // Because of this, computationally cheaper functions, when run in parallel,
        // should be summed up before more expensive functions.
        // Expected output:
        // Current result: 10000000
        // Current result: 210000000
        // Final result: 210000000
        int result = sum(functionList, onSumChanged);
        System.out.println("Final result: " + result);
    }
}
