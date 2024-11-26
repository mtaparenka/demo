import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
9. Palindrome Number
Given an integer x, return true if x is a palindrome, and false otherwise.

Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
public class Task9Test {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        char[] digits = Integer.toString(x).toCharArray();
        int left = 0;
        int right = digits.length-1;

        while (left < right) {
            if (digits[left++] != digits[right--]) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        int original = x;
        int palindrome = 0;

        while (x > 0) {
            int digit = x%10;
            palindrome = palindrome * 10 + digit;
            x = x/10;
        }

        return original == palindrome;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(int x, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, isPalindrome(x));
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test2(int x, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, isPalindrome2(x));
    }

    private static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(121, true),
                Arguments.of(-121, false),
                Arguments.of(123, false),
                Arguments.of(10, false),
                Arguments.of(10100101, true)
        );
    }
}
