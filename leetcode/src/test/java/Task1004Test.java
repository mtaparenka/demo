import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/max-consecutive-ones-iii/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task1004Test {
    public int longestOnes(int[] nums, int k) {
        int zerosCount = 0;
        int maxLength = 0;
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                zerosCount++;
            }
            if (zerosCount > k) {
                maxLength = Math.max(maxLength, right - left);

                while (zerosCount > k) {
                    if (nums[left] == 0) {
                        zerosCount--;
                    }
                    left++;
                }
            } else if (right == nums.length - 1) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            right++;
        }

        return maxLength;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int k, int expected) {
        Assertions.assertEquals(expected, longestOnes(nums, k));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2, 6),
                Arguments.of(new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3, 10),
                Arguments.of(new int[] {0,0,0,0,0,0,0}, 3, 3),
                Arguments.of(new int[] {1,1,1,1,1,1,1}, 5, 7)
        );
    }
}
