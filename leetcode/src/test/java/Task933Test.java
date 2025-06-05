import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task933Test {
    class RecentCounter {
        private final Deque<Integer> storage = new LinkedList<>();

        public RecentCounter() {

        }

        public int ping(int t) {
            storage.add(t);

            int threshold = t - 3000;

            while (!storage.isEmpty() && storage.peekFirst() < threshold) {
                storage.removeFirst();
            }

            return storage.size();
        }
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] asteroids, int[] expected) {
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
        );
    }
}
