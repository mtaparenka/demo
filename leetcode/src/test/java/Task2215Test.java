import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

 */
public class Task2215Test {
    // runtime 15%, memory 21%
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> unique1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> unique2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());

        List<Integer> diff1 = unique1.stream().filter(val -> !unique2.contains(val)).toList();
        List<Integer> diff2 = unique2.stream().filter(val -> !unique1.contains(val)).toList();

        return List.of(diff1, diff2);
    }

    // runtime 77%, memory 75%
    public List<List<Integer>> findDifferenceNoStreams(int[] nums1, int[] nums2) {
        Set<Integer> unique1 = new HashSet<>();
        Set<Integer> unique2 = new HashSet<>();

        for (int i : nums1) {
            unique1.add(i);
        }

        for (int i : nums2) {
            unique2.add(i);
        }

        List<Integer> diff1 = new ArrayList<>();
        List<Integer> diff2 = new ArrayList<>();

        for (int i : unique1) {
            if (!unique2.contains(i)) {
                diff1.add(i);
            }
        }

        for (int i : unique2) {
            if (!unique1.contains(i)) {
                diff2.add(i);
            }
        }

        return List.of(diff1, diff2);
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] nums1, int[] nums2, List<List<Integer>> expected) {
        Assertions.assertEquals(expected, findDifferenceNoStreams(nums1, nums2));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{2, 4, 6}, List.of(List.of(1, 3), List.of(4, 6)))
        );
    }
}
