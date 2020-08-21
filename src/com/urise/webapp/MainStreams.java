package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStreams {
    private static final int MIN_VALUE = minValue(new int[]{1, 2, 3, 3, 3, 2, 2, 1, 1, 9, 8, 6, 9, 9, });
    private static final List<Integer> ODD_ORR_EVEN = oddOrEven(Arrays.asList(1, 3, 5, 2));

    public static void main(String[] args) {
        System.out.println(MIN_VALUE);
        System.out.println(ODD_ORR_EVEN);
    }

    private static int minValue(int[] digits) {
        return Arrays.stream(digits).distinct().sorted().reduce(0, (d1, d2) -> d1 * 10 + d2);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> oddOreven = integers.stream()
                .collect(Collectors.partitioningBy(integer -> integer % 2 == 0));
        List<Integer> odds = oddOreven.get(false);
        return odds.size() % 2 == 1 ? oddOreven.get(true) : odds;
    }
}
