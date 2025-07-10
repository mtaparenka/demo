import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/number-of-provinces/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task547Test {
    public int findCircleNum(int[][] isConnected) {
        boolean[] isVisited = new boolean[isConnected.length];

        for (int i = 0; i < isConnected.length; i++) {
            if (!isVisited[i]) {
                dfs(i, isConnected, isVisited);
                count++;
            }
        }

        return count;
    }

    int count = 0;

    void dfs(int city, int[][] isConnected, boolean[] isVisited) {
        isVisited[city] = true;

        for (int j = 0; j < isConnected[city].length; j++) {
            if (isConnected[city][j] == 1 && !isVisited[j]) {
                dfs(j, isConnected, isVisited);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[][] isConnected, int count) {
        Assertions.assertEquals(count, findCircleNum(isConnected));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[][] {
                        {1,0,0,1},
                        {0,1,1,0},
                        {0,1,1,1},
                        {1,0,1,1},
                }, 1)
        );
    }
}
