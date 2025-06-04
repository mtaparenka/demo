import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/decode-string/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task394Test {

    int i = 0;

    public String decodeString(String s) {
        String result = "";

        while (i < s.length() && s.charAt(i) != ']') {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int amount = 0;

                while (Character.isDigit(c)) {
                    amount = amount * 10 + Character.getNumericValue(c);
                    i++;
                    c = s.charAt(i);
                }

                //skip '['
                i++;
                String add = decodeString(s);
                //skip ']'
                i++;

                for (int j = 0; j < amount; j++) {
                    result += add;
                }
            } else {
                result += c;
                i++;
            }
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String s, String expected) {
        Assertions.assertEquals(expected, decodeString(s));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("3[a2[c]]", "accaccacc")
        );
    }
}
