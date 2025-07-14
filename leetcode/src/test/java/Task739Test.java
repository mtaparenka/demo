import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/daily-temperatures/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task739Test {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        //key -> temperature, value -> index
        Deque<Map.Entry<Integer, Integer>> stack = new LinkedList<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek().getKey() <= temperatures[i]) {
                stack.poll();
            }

            if (!stack.isEmpty()) {
                answer[i] = stack.peek().getValue() - i;
            }

            stack.push(Map.entry(temperatures[i], i));
        }

        return answer;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] temperatures, int[] answer) {
        Assertions.assertArrayEquals(answer, dailyTemperatures(temperatures));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[] {73,74,75,71,69,72,76,73}, new int[] {1,1,4,2,1,1,0,0}),
                Arguments.of(new int[] {89,62,70,58,47,47,46,76,100,70}, new int[] {8,1,5,4,3,2,1,1,0,0})
        );
    }
}
