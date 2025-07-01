import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/keys-and-rooms/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task841Test {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> roomsToVisit = new HashSet<>();
        Set<Integer> roomsVisited = new HashSet<>();

        Queue<Integer> keys = new LinkedList<>(rooms.get(0));

        for (int i = 1; i < rooms.size(); i++) {
            roomsToVisit.add(i);
        }

        while (!keys.isEmpty()) {
            Integer key = keys.poll();

            if (roomsToVisit.contains(key)) {
                List<Integer> roomKeys = rooms.get(key);

                for (Integer roomKey : roomKeys) {
                    if (roomsToVisit.contains(key)) {
                        keys.add(roomKey);
                    }
                }
            }

            roomsToVisit.remove(key);

            if (roomsToVisit.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    public boolean canVisitAllRoomsDfs(List<List<Integer>> rooms) {
        boolean[] isVisited = new boolean[rooms.size()];

        dfs(0, isVisited, rooms);

        for (boolean roomVisited : isVisited) {
            if (!roomVisited) {
                return false;
            }
        }

        return true;
    }

    void dfs(int key, boolean[] isVisited, List<List<Integer>> rooms) {
        isVisited[key] = true;

        for (int roomKey : rooms.get(key)) {
            if (!isVisited[roomKey]) {
                dfs(roomKey, isVisited, rooms);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(List<List<Integer>> rooms, boolean expected) {
        Assertions.assertEquals(expected, canVisitAllRoomsDfs(rooms));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                List.of(1, 3),
                                List.of(3, 0, 1),
                                List.of(2),
                                List.of(0)
                        ), false
                )
        );
    }
}
