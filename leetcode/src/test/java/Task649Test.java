import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/dota2-senate/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task649Test {
    public String predictPartyVictory(String senate) {
        Queue<Integer> rQueue = new LinkedList<>();
        Queue<Integer> dQueue = new LinkedList<>();

        int length = senate.length();

        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                rQueue.add(i);
            } else {
                dQueue.add(i);
            }
        }

        while (!rQueue.isEmpty() && !dQueue.isEmpty()) {
            int rIndex = rQueue.poll();
            int dIndex = dQueue.poll();

            if (rIndex < dIndex) {
                rQueue.add(rIndex + length);
            } else {
                dQueue.add(dIndex + length);
            }
        }

        return rQueue.size() > dQueue.size() ? "Radiant" : "Dire";
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String senate, String expected) {
        Assertions.assertEquals(expected, predictPartyVictory(senate));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of("RD", "Radiant"),
                Arguments.of("RDD", "Dire"),
                Arguments.of("DRRD", "Dire"),
                Arguments.of("DRRDRD", "Radiant"),
                Arguments.of("DRRDRDDDDDDD", "Dire")
        );
    }
}
