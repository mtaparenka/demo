import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/increasing-triplet-subsequence
 */
public class Task334Test {
    /*
    left = 0, right 1
    iterate from 1
    if (nums[right] > nums[left])
        right ++
        if (right - left == 2)
            return true
    else
        left = right
        right++

     */
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }

        return false;
    }

    //TLE
    public boolean increasingTripletDP(int[] nums) {
        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] == 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, boolean expected) {
        Assertions.assertEquals(expected, increasingTriplet(nums));
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void testDP(int[] nums, boolean expected) {
        Assertions.assertEquals(expected, increasingTripletDP(nums));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                /*Arguments.of(new int[] {1,2,3,4,5}, true),
                Arguments.of(new int[] {5,4,3,2,1}, false),
                Arguments.of(new int[] {2,1,5,0,4,6}, true),
                Arguments.of(new int[] {1}, false),
                Arguments.of(new int[] {1,2}, false),
                Arguments.of(new int[] {1,2,3}, true),
                Arguments.of(new int[] {1,3,3}, false),
                Arguments.of(new int[] {20,100,10,12,5,13}, true),*/
                Arguments.of(new int[] {2,4,-2,-1}, false)
        );
    }
}
