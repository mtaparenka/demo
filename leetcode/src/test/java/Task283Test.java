import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/move-zeroes
 */
public class Task283Test {
    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int right = i + 1;

                while (right < nums.length && nums[right] == 0) {
                    right++;
                }

                if (right != nums.length) {
                    System.arraycopy(nums, right, nums, i, nums.length - right);
                    right = nums.length - 1 - (right - i);
                    for (int k = nums.length - 1; k > right; k--) {
                        nums[k] = 0;
                    }
                }
            }
        }
    }

    public void moveZeroesFast(int[] nums) {
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
        }
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int[] expected) {
        moveZeroesFast(nums);
        Assertions.assertArrayEquals(expected, nums);
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[] {0,1,0,3,12}, new int[] {1,3,12,0,0}),
                Arguments.of(new int[] {0,1}, new int[] {1, 0}),
                Arguments.of(new int[] {1,0}, new int[] {1, 0}),
                Arguments.of(new int[] {0}, new int[] {0}),
                Arguments.of(new int[] {1}, new int[] {1}),
                Arguments.of(new int[] {1,0,1}, new int[] {1, 1, 0}),
                Arguments.of(new int[] {0,0,1}, new int[] {1, 0, 0}),
                Arguments.of(new int[] {4,2,4,0,0,3,0,5,1,0}, new int[] {4,2,4,3,5,1,0,0,0,0})
        );
    }
}
