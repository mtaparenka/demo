import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class Task19Test {

    public static class ListNode {
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

    public ListNode removeNthFromEnd(final ListNode head, int n) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode tempHead = head;

        while (tempHead != null) {
            nodes.add(tempHead);
            tempHead = tempHead.next;
        }

        int nPos = nodes.size() - n;

        if (nPos == 0) {
            return nodes.size() > 1 ? nodes.get(1) : null;
        }

        if (nPos + 1 >= nodes.size()) {
            nodes.get(nPos - 1).next = null;
        } else {
            nodes.get(nPos - 1).next = nodes.get(nPos + 1);
        }

        return head;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(ListNode list, int n) {
        ListNode head = removeNthFromEnd(list, n);
        System.out.println(head);
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2),
                Arguments.of(new ListNode(1), 1),
                Arguments.of(new ListNode(1, new ListNode(2)), 1),
                Arguments.of(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 5)
        );
    }
}
