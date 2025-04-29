import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task1456Test {
    public int maxVowels(String s, int k) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int left = 0;
        int right = 0;
        int count = 0;
        int max = 0;

        while (right < s.length()) {
            if (vowels.contains(s.charAt(right))) {
                count++;
            }

            if (right - left >= k) {
                if (vowels.contains(s.charAt(left))) {
                    count--;
                }
                left++;
            }

            max = Math.max(max, count);

            if (count >= k) {
                return k;
            }

            right++;
        }

        return max;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String s, int k, int expected) {
        Assertions.assertEquals(expected, maxVowels(s, k));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("abciiidef", 3, 3),
                Arguments.of("aeiou", 2, 2),
                Arguments.of("weallloveyou", 7, 4),
                Arguments.of("zpuerktejfp", 3, 2)
        );
    }
}
