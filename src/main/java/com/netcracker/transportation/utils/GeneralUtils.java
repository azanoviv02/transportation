package com.netcracker.transportation.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Arrays.fill;

public class GeneralUtils {

    public static <T> List<T> flatten(List<List<T>> nestedList) {
        return nestedList
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static List<Integer> convertArrayToList(int[] array) {
        return Arrays
                .stream(array)
                .boxed()
                .collect(Collectors.toList());
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toLinkedMap(
            Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends U> valueMapper) {
        return Collectors.toMap(keyMapper, valueMapper,
                (u, v) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                },
                LinkedHashMap::new);
    }

    public static <T, S> List<S> mapList(List<T> originalList, Function<T, S> mappingFunction) {
        return originalList
                .stream()
                .map(mappingFunction)
                .collect(Collectors.toList());
    }

    public static int[] deepCopy(int[] original) {
        int length = original.length;
        int[] copy = new int[length];
        System.arraycopy(original, 0, copy, 0, length);
        return copy;
    }


    public static int[][] getFilledIntMatrix(int rows,
                                             int cols,
                                             int value) {
        int[][] matrix = new int[rows][cols];
        for (int[] line : matrix) {
            fill(line, value);
        }
        return matrix;
    }

    public static double[][] getFilledDoubleMatrix(int rows,
                                                   int cols,
                                                   double value) {
        double[][] matrix = new double[rows][cols];
        for (double[] line : matrix) {
            fill(line, value);
        }
        return matrix;
    }
}
