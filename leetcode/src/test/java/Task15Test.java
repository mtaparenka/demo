import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/3sum/description/
 */
public class Task15Test {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    result.add(List.of(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return result.stream().toList();
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(List<List<Integer>> expected, int[] nums) {
        List<Integer> threeSumFlat = threeSum(nums).stream().flatMap(Collection::stream).toList();
        List<Integer> expectedFlat = expected.stream().flatMap(Collection::stream).toList();

        Assertions.assertEquals(expectedFlat.size(), threeSumFlat.size());
        Assertions.assertTrue(expectedFlat.containsAll(threeSumFlat));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)), new int[]{-1, 0, 1, 2, -1, -4}),
                Arguments.of(List.of(), new int[]{0, 1, 1}),
                Arguments.of(List.of(List.of(0, 0, 0)), new int[]{0, 0, 0, 0}),
                Arguments.of(List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)), new int[]{-2, 0, 1, 1, 2}),
                Arguments.of(List.of(List.of(-2,-1,3), List.of(-2,0,2), List.of(-1,0,1)), new int[]{3, 0, -2, -1, 1, 2})
                );
    }
}
