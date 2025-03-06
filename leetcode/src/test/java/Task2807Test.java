import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/description/
 */
public class Task2807Test {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    ListNode insertGreatestCommonDivisors(final ListNode head) {
        ListNode current = head;

        while (current.next != null) {
            ListNode next = current.next;
            int div = 1;
            int min = Math.min(current.val, next.val);

            for (int i = min; i > div; i--) {
                if (current.val % i == 0 && next.val % i == 0) {
                    div = i;
                    break;
                }
            }

            current.next = new ListNode(div, next);
            current = next;
        }

        return head;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(ListNode list) {
        ListNode actual = insertGreatestCommonDivisors(list);
        System.out.println(actual);
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new ListNode(18, new ListNode(6, new ListNode(10, new ListNode(3))))),
                Arguments.of(new ListNode(7)),
                Arguments.of(new ListNode(18, new ListNode(6, new ListNode(10))))
        );
    }
}
