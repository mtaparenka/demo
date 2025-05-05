import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task1493Test {
    public int longestSubarray(int[] nums) {
        int max = 0;
        int left = 0;
        int right = 0;
        int zeroIndex = -1;

        while (right < nums.length) {
            if (nums[right] == 0) {
                if (zeroIndex != -1) {
                    max = Math.max(max, right - left - 1);
                    left = zeroIndex + 1;
                }

                zeroIndex = right;
            } else if (right == nums.length - 1) {
                max = Math.max(max, right - left);
            }

            right++;
        }

        return max;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int expected) {
        Assertions.assertEquals(expected, longestSubarray(nums));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 0, 1}, 3),
                Arguments.of(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}, 5),
                Arguments.of(new int[]{0, 1, 1, 1, 1, 1, 1, 1, 0}, 7),
                Arguments.of(new int[]{1, 1, 1}, 2)
        );
    }
}
