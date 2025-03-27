import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/partition-array-according-to-given-pivot/description/
 */
public class Task2161Test {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] result = new int[nums.length];
        List<Integer> less = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        List<Integer> pivots = new ArrayList<>();

        for (int n: nums) {
            if (n < pivot) {
                less.add(n);
            } else if (n > pivot) {
                greater.add(n);
            } else {
                pivots.add(n);
            }
        }

        less.addAll(pivots);
        less.addAll(greater);

        for (int i = 0; i < less.size(); i++) {
            result[i] = less.get(i);
        }

        return result;
    }

    public int[] pivotArrayFast(int[] nums, int pivot) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;

        for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
            if (nums[i] < pivot) {
                result[left++] = nums[i];
            }

            if (nums[j] > pivot) {
                result[right--] = nums[j];
            }
        }

        for (int i = left; i <= right; i++) {
            result[i] = pivot;
        }

        return result;
    }

    public int[] pivotArrayStreams(int[] nums, int pivot) {
        return IntStream.concat(
                IntStream.of(nums).filter(n -> n < pivot),
                IntStream.concat(IntStream.of(nums).filter(n -> n == pivot), IntStream.of(nums).filter(n -> n > pivot))
        ).toArray();
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums, int pivot, int[] expected) {
        Assertions.assertArrayEquals(expected, pivotArrayFast(nums, pivot));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[]{9, 12, 5, 10, 14, 3, 10}, 10, new int[]{9, 5, 3, 10, 10, 12, 14}),
                Arguments.of(new int[]{-3, 4, 3, 2}, 2, new int[]{-3, 2, 4, 3})
        );
    }
}
