import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*

 */
public class Task1071Test {
    public String gcdOfStrings(String str1, String str2) {
        if ((str1 + str2).equals(str2 + str1)) {
            int maxLength = Math.max(str1.length(), str2.length());

            for (int i = maxLength; i > 0; i--) {
                if (str1.length() % i == 0 && str2.length() % i == 0) {
                    return str1.substring(0, i);
                }
            }
        }

        return "";
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String str1, String str2, String expected) {
        Assertions.assertEquals(expected, gcdOfStrings(str1, str2));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("ABCABC", "ABC", "ABC"),
                Arguments.of("ABABAB", "ABAB", "AB"),
                Arguments.of("LEET", "CODE", "")
        );
    }
}
