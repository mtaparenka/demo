import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
4. Median of Two Sorted Arrays

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).



Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 */
public class Task4Test {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] resultArray = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, resultArray, 0, nums1.length);
        System.arraycopy(nums2, 0, resultArray, nums1.length, nums2.length);

        Arrays.sort(resultArray);
        int medianIndex = resultArray.length/2;

        if (resultArray.length % 2 == 0) {
            return (resultArray[medianIndex] + resultArray[medianIndex-1])/2d;
        }

        return resultArray[medianIndex];
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(double expected, int[] nums1, int[] nums2) {
        assertEquals(expected, findMedianSortedArrays(nums1, nums2));
    }

    private static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(2.0d, new int[]{1,3}, new int[]{2}),
                Arguments.of(2.5d, new int[]{1,2}, new int[]{3,4}),
                Arguments.of(3d, new int[]{1,5}, new int[]{2,3,4}),
                Arguments.of(2d, new int[]{}, new int[]{2})
        );
    }

}
