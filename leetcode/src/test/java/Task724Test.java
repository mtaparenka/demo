import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/find-pivot-index/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task724Test {
    public int pivotIndex(int[] nums) {
        int[] sumLeft = new int[nums.length + 1];
        int[] sumRight = new int[nums.length + 1];

        for (int i = 1; i < sumLeft.length; i++) {
            sumLeft[i] = sumLeft[i - 1] + nums[i - 1];
            int reversedIndex = sumLeft.length - 1 - i;
            sumRight[reversedIndex] = sumRight[reversedIndex + 1] + nums[reversedIndex];
        }

        for (int i = 0; i < sumLeft.length - 1; i++) {
            if (sumLeft[i] == sumRight[i + 1]) {
                return i;
            }
        }

        return -1;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int expected) {
        Assertions.assertEquals(expected, pivotIndex(nums));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
            Arguments.of(new int[] {1,7,3,6,5,6}, 3),
            Arguments.of(new int[] {1,2,3}, -1),
            Arguments.of(new int[] {2,1,-1}, 0),
            Arguments.of(new int[] {0,0,0}, 0)
        );
    }
}
