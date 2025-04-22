import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*

 */
public class Task392Test {
    public boolean isSubsequence(String s, String t) {
        if (t.length() < s.length()) {
            return false;
        }

        if (s.isEmpty()) {
            return true;
        }

        int left = 0;

        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(left) == t.charAt(i)) {
                left++;

                if (left == s.length()) {
                    return true;
                }
            }
        }

        return false;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String s, String t, boolean expected) {
        Assertions.assertEquals(expected, isSubsequence(s, t));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("abc", "ahbgdc", true),
                Arguments.of("axc", "ahbgdc", false),
                Arguments.of("a", "a", true),
                Arguments.of("a", "b", false),
                Arguments.of("abc", "bc", false),
                Arguments.of("", "bc", true),
                Arguments.of("a", "", false),
                Arguments.of("", "", true)
        );
    }
}
