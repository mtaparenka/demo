import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task735Test {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();

        for (Integer newAsteroid : asteroids) {
            Integer asteroid = stack.peekLast();

            while (!stack.isEmpty() && asteroid > 0 && newAsteroid != null && newAsteroid < 0) {
                int abs = Math.abs(newAsteroid);

                if (asteroid > abs) {
                    newAsteroid = null;
                } else if (asteroid < abs) {
                    stack.removeLast();
                    asteroid = stack.peekLast();
                } else {
                    stack.removeLast();
                    newAsteroid = null;
                }
            }

            if (newAsteroid != null) {
                stack.add(newAsteroid);
            }
        }

        int[] result = new int[stack.size()];
        int size = stack.size();

        for (int i = 0; i < size; i++) {
            result[i] = stack.poll();
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] asteroids, int[] expected) {
        Assertions.assertArrayEquals(expected, asteroidCollision(asteroids));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[]{5, 10, -5}, new int[]{5, 10}),
                Arguments.of(new int[]{10, 2, -5}, new int[]{10}),
                Arguments.of(new int[]{8,-8}, new int[]{}),
                Arguments.of(new int[]{10}, new int[]{10})
        );
    }
}
