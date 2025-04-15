import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://leetcode.com/problems/can-place-flowers/description
 */
public class Task605Test {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;

        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                i++;
            } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                count++;
                i++;
            }
        }

        return n <= count;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int[] flowerbed, int n, boolean expected) {
        Assertions.assertEquals(expected, canPlaceFlowers(flowerbed, n));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new int[]{1, 0, 0, 0, 1}, 1, true),
                Arguments.of(new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1}, 2, true),
                Arguments.of(new int[]{1, 0, 0, 0, 1}, 2, false),
                Arguments.of(new int[]{1}, 0, true),
                Arguments.of(new int[]{0}, 1, true),
                Arguments.of(new int[]{0, 1, 0}, 1, false),
                Arguments.of(new int[]{0,0,1,0,1}, 1, true),
                Arguments.of(new int[]{1,0,0,0,1,0,0}, 2, true)
        );
    }
}
