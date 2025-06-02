import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/equal-row-and-column-pairs/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task2352Test {
    public int equalPairs(int[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[0][i] == grid[j][0]) {
                    int index = 1;

                    while (index < grid.length && grid[index][i] == grid[j][index]) {
                        index++;
                    }

                    if (index == grid.length) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public int equalPairsHash(int[][] grid) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            int rowHash = 0;

            for (int j = 0; j < grid.length; j++) {
                rowHash = grid[i][j] + rowHash * 5;
            }

            map.put(rowHash, map.getOrDefault(rowHash, 0) + 1);
        }
        for (int i = 0; i < grid.length; i++) {
            int colHash = 0;

            for (int j = 0; j < grid.length; j++) {
                colHash = grid[j][i] + colHash * 5;
            }

            if (map.containsKey(colHash)) {
                count += map.get(colHash);
            }
        }

        return count;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[][] grid, int expected) {
        Assertions.assertEquals(expected, equalPairs(grid));
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test2(int[][] grid, int expected) {
        Assertions.assertEquals(expected, equalPairsHash(grid));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}}, 1),
                Arguments.of(new int[][]{{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}}, 3),
                Arguments.of(new int[][]{{11, 1}, {1, 11}}, 2)
        );
    }
}
