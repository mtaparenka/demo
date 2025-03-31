import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/description/
 */
public class Task1689Test {

    /*
        represent n chars as array of length i


     */
    public int minPartitions(String n) {
        int max = n.chars().max().orElse(0);
        return (char)max - '0';
    }

    public int minPartitions2(String n) {
        char[] c = n.toCharArray();
        Arrays.sort(c);

        return Character.getNumericValue(c[c.length - 1]);
    }

    public int minPartitions3(String n) {
        char result = 0;

        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);

            if (c == 57) {
                return 9;
            } else if (c > result){
                result = c;
            }

        }

        return Character.getNumericValue(result);
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String n, int expected) {
        Assertions.assertEquals(expected, minPartitions3(n));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("1234", 4),
                Arguments.of("27346209830709182346", 9)
        );
    }
}
