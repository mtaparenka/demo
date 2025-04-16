import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/reverse-vowels-of-a-string/description
 */
public class Task345Test {

    // IceCreAm -> AceCreIm
    public String reverseVowels(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            if (vowels.contains(chars[left])) {
                while (left < right && !vowels.contains(chars[right])) {
                    right--;
                }

                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                right--;
            }

            left++;
        }

        return new String(chars);
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String s, String expected) {
        Assertions.assertEquals(expected, reverseVowels(s));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("IceCreAm", "AceCreIm"),
                Arguments.of("BZZZA", "BZZZA"),
                Arguments.of("BZZZ", "BZZZ")
        );
    }
}
