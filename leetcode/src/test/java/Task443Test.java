import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/string-compression
 */
public class Task443Test {
    public int compress(char[] chars) {
        StringBuilder sb = new StringBuilder();
        char current = chars[0];
        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == current) {
                count++;
            } else {
                append(sb, current, count);
                current = chars[i];
                count = 1;
            }

            if (i == chars.length - 1) {
                append(sb, current, count);
            }
        }

        char[] compressed = sb.toString().toCharArray();
        System.arraycopy(compressed, 0, chars, 0, compressed.length);

        return sb.length();
    }

    private static void append(StringBuilder sb, char current, int count) {
        sb.append(current);

        if (count != 1) {
            sb.append(count);
        }
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(char[] chars, int expected) {
        Assertions.assertEquals(expected, compress(chars));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new char[] {'a','a','b','b','c','c','c'}, 6),
                Arguments.of(new char[] {'a'}, 1),
                Arguments.of(new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'}, 4)
        );
    }
}
