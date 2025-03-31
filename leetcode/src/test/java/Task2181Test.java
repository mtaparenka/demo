import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/merge-nodes-in-between-zeros/
 */
public class Task2181Test {


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

    /*
        1. create new list
        2. take element from head
            if 0 add 1 to count
            if non-zero add to tempSum

        3. take next element
            if 0 add 1 to count, if count = 2 -> add tempSum to new list, count = 1, jump to next element if not null
            if non-zero add to tempSum
        4. repeat until head.next != null


     */
    public ListNode mergeNodes(ListNode head) {
        ListNode result = new ListNode();
        ListNode resultCurr = result;
        int sum = 0;

        while (head != null) {
            int value = head.val;

            if (value == 0) {
                if (sum > 0) {
                    resultCurr.next = new ListNode(sum);
                    resultCurr = resultCurr.next;
                    sum = 0;
                }
            } else {
                sum += value;
            }

            head = head.next;
        }

        return result.next;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(ListNode head) {
        var res = mergeNodes(head);
        System.out.println(res);
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new ListNode(0, new ListNode(3, new ListNode(1, new ListNode(0, new ListNode(4, new ListNode(5, new ListNode(2, new ListNode(0)))))))))
        );
    }
}
