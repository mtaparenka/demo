import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/max-number-of-k-sum-pairs
 */
public class Task1679Test {

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int count = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum > k) {
                right--;
            } else if (sum < k) {
                left++;
            } else {
                count++;
                left++;
                right--;
            }
        }

        return count;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int k, int expected) {
        Assertions.assertEquals(expected, maxOperations(nums, k));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[] {1,2,3,4}, 5, 2),
                Arguments.of(new int[] {3,1,3,4,3}, 6, 1),
                Arguments.of(new int[] {1, 1}, 1, 0),
                Arguments.of(new int[] {1}, 1, 0)
        );
    }
}
