import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/unique-number-of-occurrences/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task1207Test {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> result = new HashSet<>();

        for (int val : arr) {
            if (map.containsKey(val)) {
                map.put(val, map.get(val) + 1);
            } else {
                map.put(val, 1);
            }
        }

        for (int val : map.values()) {
            if (!result.add(val)) {
                return false;
            }
        }

        return true;
    }

    public boolean uniqueOccurrencesStreams(int[] arr) {
        Set<Long> set = new HashSet<>();
        return Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(val -> val, Collectors.counting()))
                .values().stream()
                .allMatch(set::add);
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] arr, boolean expected) {
        Assertions.assertEquals(expected, uniqueOccurrencesStreams(arr));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 2, 1, 1, 3}, true),
                Arguments.of(new int[]{1, 2}, false),
                Arguments.of(new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}, true)
        );
    }
}
