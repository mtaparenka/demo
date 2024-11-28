import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
11. Container With Most Water

You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.
Notice that you may not slant the container.

Examples: see https://leetcode.com/problems/container-with-most-water/ (contains pictures)
 */
public class Task11Test {
    public int maxArea(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int l = height[left];
            int r = height[right];
            result = Math.max(result, (right - left) * Math.min(l, r));

            if (l < r) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] height, int expected) {
        Assertions.assertEquals(expected, maxArea(height));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[] {1, 1}, 1),
                Arguments.of(new int[] {1,8,6,2,5,4,8,3,7}, 49),
                Arguments.of(new int[] {8,7,2,1}, 7),
                Arguments.of(new int[] {1,0,0,0,0,0,0,2,2}, 8),
                Arguments.of(new int[] {2,3,4,5,18,17,6}, 17),
                Arguments.of(new int[] {1,2,4,3}, 4)
        );
    }
}
