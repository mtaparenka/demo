import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
10. Regular Expression Matching

Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).


Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Constraints:

1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class Task10Test {
    public boolean isMatch(String s, String p) {
        //learn DP, gg for now

        return true;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String s, String p, boolean expected) {
        Assertions.assertEquals(expected, isMatch(s, p));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("aa", "a", false),
                Arguments.of("aa", "a*", true),
                Arguments.of("ab", ".*", true),
                Arguments.of("aaaasasa", ".*sasa", true),
                Arguments.of("abcasasazzzz", ".*sasaz*", true)
        );
    }
}
