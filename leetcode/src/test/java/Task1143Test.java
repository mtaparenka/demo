import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/longest-common-subsequence/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task1143Test {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String text1, String text2, int expected) {
        Assertions.assertEquals(expected, longestCommonSubsequence(text1, text2));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("abcde", "ace", 3),
                Arguments.of("ace", "abcde", 3)
        );
    }
}
