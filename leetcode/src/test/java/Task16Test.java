import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/3sum-closest/
 */
public class Task16Test {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];

        if (nums.length == 3) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == target) {
                    return sum;
                }

                if (Math.abs(target - sum) <= Math.abs(target - result)) {
                    result = sum;
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int target, int expected) {
        Assertions.assertEquals(expected, threeSumClosest(nums, target));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[]{-1, 2, 1, -4}, 1, 2),
                Arguments.of(new int[]{0, 0, 0}, 1, 0)
        );
    }
}
