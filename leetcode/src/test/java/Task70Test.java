import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/climbing-stairs/
 */
public class Task70Test {
    public int climbStairsHonest(int n) {
        List<List<Integer>> ways = new ArrayList<>();

        climbStairsBacktracking(ways, new ArrayList<>(50), 0, n);

        return ways.size();
    }

    public void climbStairsBacktracking(List<List<Integer>> ways, List<Integer> currentWay, int sum, int n) {
        if (sum == n) {
            ways.add(new ArrayList<>(currentWay));
            return;
        }

        if (sum > n) {
            return;
        }

        currentWay.add(1);
        climbStairsBacktracking(ways, currentWay, sum + 1, n);
        currentWay.removeLast();

        currentWay.add(2);
        climbStairsBacktracking(ways, currentWay, sum + 2, n);
        currentWay.removeLast();
    }

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int first = 1;
        int second = 2;

        for (int i = 3; i <= n; i++) {
            int cur = first + second;
            first = second;
            second = cur;
        }

        return second;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int n, int expected) {
        Assertions.assertEquals(expected, climbStairs(n));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3)
        );
    }
}
