import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task2095Test {
    public class ListNode {
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

    public ListNode deleteMiddle(ListNode head) {
        List<ListNode> cache = new ArrayList<>();
        ListNode headRef = head;

        while (head != null) {
            cache.add(head);
            head = head.next;
        }

        int midIndex = cache.size()/2;
        ListNode mid = cache.get(midIndex);

        if (cache.size() == 1) {
            return null;
        } else {
            ListNode prev = cache.get(midIndex - 1);

            prev.next = mid.next;
        }

        return headRef;
    }

    public ListNode deleteMiddleSlowFast(ListNode head) {
        if (head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test() {
        deleteMiddleSlowFast(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
            Arguments.of()
        );
    }
}
