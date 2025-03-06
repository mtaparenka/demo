import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/*
https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/description/
 */
public class Task1769Test {
    public static int[] minOperations(String boxes) {
        int[] result = new int[boxes.length()];

        for (int i = 0; i < result.length; i++) {
            int operationsCount = 0;

            for (int j = 0; j < boxes.length(); j++) {
                if (boxes.charAt(j) == '1') {
                    operationsCount += Math.abs(i - j);
                }
            }

            result[i] = operationsCount;
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String boxes, int[] expected) {
        assertArrayEquals(expected, minOperations(boxes));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("110", new int[]{1, 1, 3}),
                Arguments.of("001011", new int[]{11, 8, 5, 4, 3, 4})
        );
    }
}
