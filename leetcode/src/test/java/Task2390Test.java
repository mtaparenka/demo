import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/removing-stars-from-a-string/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task2390Test {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '*' && !sb.isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String s, String expected) {
        Assertions.assertEquals(expected, removeStars(s));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("leet**cod*e", "lecoe"),
                Arguments.of("erase*****", "")
        );
    }
}
