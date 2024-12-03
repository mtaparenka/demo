import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/roman-to-integer/description/
 */
public class Task13Test {
    Map<Character, Integer> map = Map.of(
            'M', 1000,
            'D', 500,
            'C', 100,
            'L', 50,
            'X', 10,
            'V', 5,
            'I', 1
    );

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int lastValue = map.get(chars[0]);
        int result = 0;

        for (char aChar : chars) {
            int currentValue = map.get(aChar);

            if (lastValue < currentValue) {
                result -= (2 * lastValue);
            }

            result += currentValue;
            lastValue = currentValue;
        }

        return result;
    }

    public int romanToInt2(String s) {
        char[] chars = s.toCharArray();
        int lastValue = 0;
        int result = 0;

        for (int i = chars.length - 1; i >=0; i--) {
            int currentValue = map.get(chars[i]);

            if (currentValue < lastValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            lastValue = currentValue;
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int expected, String number) {
        Assertions.assertEquals(expected, romanToInt(number));
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test2(int expected, String number) {
        Assertions.assertEquals(expected, romanToInt2(number));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(1994, "MCMXCIV"),
                Arguments.of(3749, "MMMDCCXLIX"),
                Arguments.of(58, "LVIII"),
                Arguments.of(4, "IV")
        );
    }
}
