import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/find-the-original-array-of-prefix-xor/description/
 */
public class Task2433Test {

    /*
        - for storing result create new array with pref.length
        - if pref.length = 1 return new array with pref[0] element
        - start iterating from second element
        - take i element from pref array
        - perform xor on pref[i-1] and pref[i]
     */
    public int[] findArray(int[] pref) {
        int[] result = new int[pref.length];
        result[0] = pref[0];

        if (pref.length == 1) {
            return result;
        }

        for (int i = 1; i < pref.length; i++) {
            result[i] = pref[i-1] ^ pref[i];
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] pref, int[] expected) {
        Assertions.assertArrayEquals(expected, findArray(pref));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[] {5,2,0,3,1}, new int[] {5,7,2,3,2})
        );
    }
}
