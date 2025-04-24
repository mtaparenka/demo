import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*

 */
public class Task643Test {
    public double findMaxAverage(int[] nums, int k) {
        int sum = Integer.MIN_VALUE;

        for (int i = 0; i <= nums.length - k; i++) {
            int currentSum = 0;
            for (int j = i; j < i + k; j++) {
                currentSum += nums[j];
            }

            sum = Math.max(currentSum, sum);
        }

        return (double)sum / k;
    }

    public double findMaxAverageOptimized(int[] nums, int k) {
        int sum = Arrays.stream(nums).limit(k).sum();
        int maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];

            maxSum = Math.max(maxSum,sum);
        }

        return (double)maxSum / k;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int k, double expected) {
        Assertions.assertEquals(expected, findMaxAverage(nums, k));
        Assertions.assertEquals(expected, findMaxAverageOptimized(nums, k));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[] {1,12,-5,-6,50,3}, 4, 12.75000),
                Arguments.of(new int[] {5}, 1, 5.00000),
                Arguments.of(new int[] {0,1,1,3,3}, 4, 2.00000)
        );
    }
}
