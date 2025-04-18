import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/product-of-array-except-self/description
 */
public class Task238Test {
    //too slow, got TLE
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            boolean visited = false;

            for (int j = 0; j < nums.length; j++) {
                if (j != i) {
                    if (visited) {
                        result[i] = result[i] * nums[j];
                    } else {
                        result[i] = nums[j];
                        visited = true;
                    }
                }
            }
        }

        return result;
    }

    //[5, 2, 3, 4]
    public int[] productExceptSelf2(int[] nums) {
        int mul = 1;
        int mulNoZero = 1;

        for (int num : nums) {
            mul *= num;

            if (num != 0) {
                mulNoZero *= num;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[i] = mul / nums[i];
            } else {
                nums[i] = mulNoZero;
            }
        }

        return nums;
    }

    public int[] productExceptSelfNoDivision(int[] nums) {
        int[] result = new int[nums.length];

        result[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int multiplier = 1;
        for (int i = nums.length - 1; i >= 0 ; i--) {
            result[i] = result[i] * multiplier;
            multiplier = multiplier * nums[i];
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int[] expected) {
        Assertions.assertArrayEquals(expected, productExceptSelf(nums));
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test2(int[] nums, int[] expected) {
        Assertions.assertArrayEquals(expected, productExceptSelf2(nums));
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void testNoDiv(int[] nums, int[] expected) {
        Assertions.assertArrayEquals(expected, productExceptSelfNoDivision(nums));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6}),
                Arguments.of(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0}),
                Arguments.of(new int[]{5, 2, 3, 4, 0}, new int[]{0, 0, 0, 0, 120})
        );
    }
}
