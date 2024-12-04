import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/longest-common-prefix/description/
 */
public class Task14Test {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            for (String str : strs) {
                if (str.length() <= i || str.charAt(i) != c) {
                    return result.toString();
                }
            }
            result.append(c);
        }

        return result.toString();
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String expected, String[] strs) {
        Assertions.assertEquals(expected, longestCommonPrefix(strs));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("fl", new String[]{"flower","flow","flight"}),
                Arguments.of("", new String[]{"dog","racecar","car"}),
                Arguments.of("a", new String[]{"a"}),
                Arguments.of("a", new String[]{"ab", "a"})
        );
    }
}
