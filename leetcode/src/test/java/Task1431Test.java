import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/description
 */
public class Task1431Test {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = IntStream.of(candies).max().orElse(0);

        return Arrays.stream(candies)
                .mapToObj(amount -> amount + extraCandies >= max)
                .toList();
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test() {
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
        );
    }
}
