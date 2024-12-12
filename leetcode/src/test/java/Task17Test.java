import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class Task17Test {
    private static final Map<String, String> map = Map.of(
            "2", "abc",
            "3", "def",
            "4", "ghi",
            "5", "jkl",
            "6", "mno",
            "7", "pqrs",
            "8", "tuv",
            "9", "wxyz"
    );
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.isEmpty()) {
            return result;
        }

        if (digits.length() == 1) {
            String letters = map.get(digits.charAt(0) + "");

            for (int i = 0; i < letters.length(); i++) {
                result.add(letters.charAt(i) + "");
            }

            return result;
        } else {
            for (int i = 0; i < digits.length(); i++) {
                if (result.isEmpty()) {
                    result = letterCombinations(digits.charAt(i) + "");
                } else {
                    List<String> newComb = letterCombinations(digits.charAt(i) + "");
                    List<String> newRes = new ArrayList<>();

                    for (String string : result) {
                        for (String s : newComb) {
                            newRes.add(string + s);
                        }
                    }

                    result = newRes;
                }
            }
        }

        return result;
    }

    public List<String> letterCombinationsBacktrack(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.isEmpty()) {
            return result;
        }

        backtrack(digits, 0, "", result);

        return result;
    }

    private void backtrack(String digits, int index, String comb, List<String> result) {
        if (index == digits.length()) {
            result.add(comb);
        } else {
            String letters = map.get(digits.charAt(index) + "");

            for (int i = 0; i < letters.length(); i++) {
                comb += letters.charAt(i);
                backtrack(digits, index+1, comb, result);
                comb = comb.substring(0, comb.length() - 1);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String digits, List<String> expected) {
        var combinations = letterCombinations(digits);

        Assertions.assertEquals(expected.size(), combinations.size());
        Assertions.assertTrue(expected.containsAll(combinations));
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void testBacktrack(String digits, List<String> expected) {
        var combinations = letterCombinationsBacktrack(digits);

        Assertions.assertEquals(expected.size(), combinations.size());
        Assertions.assertTrue(expected.containsAll(combinations));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
            Arguments.of("23", List.of("ad","ae","af","bd","be","bf","cd","ce","cf")),
            Arguments.of("2", List.of("a","b","c")),
            Arguments.of("", List.of())
        );
    }
}
