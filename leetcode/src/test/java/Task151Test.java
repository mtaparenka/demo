import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/reverse-words-in-a-string/description
 */
public class Task151Test {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");

        for (int i = 0; i < words.length/2; i++) {
            String tmp = words[i];
            words[i] = words[words.length - 1 - i];
            words[words.length - 1 - i] = tmp;
        }

        return String.join(" ", words);
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String s, String expected) {
        Assertions.assertEquals(expected, reverseWords(s));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("  hello  world  ", "world hello")
        );
    }
}
