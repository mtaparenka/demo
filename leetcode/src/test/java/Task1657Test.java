import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/determine-if-two-strings-are-close/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task1657Test {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        Map<Character, Integer> word1CharOccurrencesCount = new HashMap<>();
        Map<Character, Integer> word2CharOccurrencesCount = new HashMap<>();

        for (int i = 0; i < word1.length(); i++) {
            word1CharOccurrencesCount.compute(word1.charAt(i), (k, v) -> v == null ? 1 : v + 1);
            word2CharOccurrencesCount.compute(word2.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }

        List<Integer> values1 = new ArrayList<>(word1CharOccurrencesCount.values());
        List<Integer> values2 = new ArrayList<>(word2CharOccurrencesCount.values());

        values1.sort(Integer::compareTo);
        values2.sort(Integer::compareTo);

        return word1CharOccurrencesCount.keySet().equals(word2CharOccurrencesCount.keySet()) && values1.equals(values2);
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String word1, String word2, boolean expected) {
        Assertions.assertEquals(expected, closeStrings(word1, word2));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("abc", "bca", true),
                Arguments.of("a", "aa", false),
                Arguments.of("cabbba", "abbccc", true),
                Arguments.of("a", "b", false),
                Arguments.of("uau", "ssx", false)
        );
    }
}
